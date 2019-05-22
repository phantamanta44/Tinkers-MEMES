package io.github.phantamanta44.tmemes.coremod;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.*;

import javax.annotation.Nullable;

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

        @Nullable
        private String className = null;

        RenderItemTransformer(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            this.className = name;
            super.visit(version, access, name, signature, superName, interfaces);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            String mappedName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(className, name, desc);
            if ((mappedName.equals("renderItemOverlayIntoGUI") || mappedName.equals("func_180453_a"))
                    && Type.getReturnType(desc) == Type.VOID_TYPE) {
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
            String mappedName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(owner, name, desc);
            if (state == 0) {
                if (opcode == Opcodes.INVOKEVIRTUAL && mappedName.equals("showDurabilityBar")) {
                    state = 1;
                }
            } else if (state == 1) {
                if (opcode == Opcodes.INVOKESTATIC
                        && (mappedName.equals("getMinecraft") || mappedName.equals("func_71410_x"))) {
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
            System.out.println("Successfully injected energy bar thing!");
        }

    }

}
