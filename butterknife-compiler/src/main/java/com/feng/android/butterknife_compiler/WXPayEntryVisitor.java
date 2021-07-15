package com.feng.android.butterknife_compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 09:54
 * @tips
 */
public class WXPayEntryVisitor extends SimpleAnnotationValueVisitor7<Void,Void> {
    private String mPackageName;
    private TypeMirror mTypeMirror;
    private Filer mFiler;

    @Override
    public Void visitString(String s, Void unused) {
        mPackageName = s;
        return unused;
    }

    @Override
    public Void visitType(TypeMirror t, Void unused) {
        mTypeMirror = t;
        generateWXPayCode();
        return unused;
    }

    public void setFiler(Filer mFiler){
        this.mFiler = mFiler;
    }

    /**
     * 生成专门的类
     */
    private void generateWXPayCode() {
        //生成一个Class xxx.wxapi.WXPayEntryActivity extends BaseWXPayActivity
        TypeSpec.Builder classSpecBuilder = TypeSpec.classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror));

        try {
            JavaFile.builder(mPackageName+".wxapi",classSpecBuilder.build())
                    .addFileComment("微信支付自动生成")
                    .build().writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
