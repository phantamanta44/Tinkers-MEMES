package io.github.phantamanta44.tmemes.coremod;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

public class MemeClassTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] code) {
        if (transformedName.equals("net.minecraft.client.renderer.RenderItem")) {
            ClassReader reader = new ClassReader(code);
            ClassWriter writer = new ClassWriter(reader, 0);
            reader.accept(new RenderItemTransformer(Opcodes.ASM5, writer), 0);
            return writer.toByteArray();
        }
        return code;
    }

    private static class RenderItemTransformer extends ClassVisitor {

        RenderItemTransformer(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if ((name.equals("renderItemOverlayIntoGUI") || name.equals("func_180453_a"))
                    && Type.getArgumentTypes(desc).length == 5 && Type.getReturnType(desc) == Type.VOID_TYPE) {
                return new RenderItemOverlayIntoGuiTransformer(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class RenderItemOverlayIntoGuiTransformer extends MethodVisitor {

        private int state = 0;

        RenderItemOverlayIntoGuiTransformer(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (state == 0) {
                if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("net/minecraft/item/Item")
                        && Type.getReturnType(desc) == Type.BOOLEAN_TYPE) {
                    Type[] argTypes = Type.getArgumentTypes(desc);
                    if (argTypes.length == 1 && argTypes[0].getInternalName().equals("net/minecraft/item/ItemStack")) {
                        state = 1;
                    }
                }
            } else if (state == 1) {
                if (opcode == Opcodes.INVOKESTATIC && (name.equals("getMinecraft") || name.equals("func_71410_x"))
                        && owner.equals("net/minecraft/client/Minecraft")) {
                    inject();
                    state = 2;
                }
            }
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        }

        private void inject() {
            super.visitVarInsn(Opcodes.ALOAD, 2);
            super.visitVarInsn(Opcodes.ILOAD, 3);
            super.visitVarInsn(Opcodes.ILOAD, 4);
            super.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "io/github/phantamanta44/tmemes/client/MemeRenderInterceptor",
                    "handleRender",
                    "(Lnet/minecraft/item/ItemStack;II)V",
                    false);
        }

    }

}
