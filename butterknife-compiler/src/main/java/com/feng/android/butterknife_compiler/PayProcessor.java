package com.feng.android.butterknife_compiler;

import com.feng.android.butterknife_annotations.BindView;
import com.feng.android.butterknife_annotations.WXPayEntry;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 09:43
 * @tips
 */
@AutoService(Processor.class)
public class PayProcessor extends AbstractProcessor {

    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
    }

    //指定需要处理的Annotation
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        System.out.println("--------------------> getSupportedAnnotationTypes");

        Set<String> types = new LinkedHashSet<>();
        for(Class<? extends Annotation> annotation : getSupportedAnnotations()){
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        //需要解析的自定义注解 WXPayEntry
        annotations.add(WXPayEntry.class);
        return annotations;
    }

    //指定需要支持的版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //生成一个Class xxx.wxapi.WXPayEntryActivity extends BaseWXPayActivity
        generateWXPayCode(roundEnv);
        return false;
    }

    private void generateWXPayCode(RoundEnvironment roundEnv) {
        WXPayEntryVisitor visitor = new WXPayEntryVisitor();
        visitor.setFiler(mFiler);
        scanElement(roundEnv,WXPayEntry.class,visitor);
    }

    private void scanElement(RoundEnvironment roundEnv, Class<? extends Annotation> annotation, AnnotationValueVisitor visitor){
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
        for(Element element : elements){
            List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();

            for(AnnotationMirror annotationMirror : annotationMirrors){
                Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues =
                        annotationMirror.getElementValues();

                for(Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry:elementValues.entrySet()){
                    entry.getValue().accept(visitor,null);
                }


            }

        }

    }
}
