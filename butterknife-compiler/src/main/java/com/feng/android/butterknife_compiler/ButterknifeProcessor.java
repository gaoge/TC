package com.feng.android.butterknife_compiler;

import com.feng.android.butterknife_annotations.BindView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-13 16:36
 * @tips
 */
@AutoService(Processor.class)
public class ButterknifeProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
    }

    //1.指定处理的版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        System.out.println("--------------------> getSupportedSourceVersion");

        return SourceVersion.latestSupported();
    }

    //2.给到需要处理的注解
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
        //需要解析的自定义注解 BindView OnClick
        annotations.add(BindView.class);
        return annotations;
    }

    //process方法代表的是，有注解就会进来，但是这里是一团乱码
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("-------------------->");
        System.out.println("-------------------->");
        System.out.println("-------------------->");
        System.out.println("-------------------->");

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(BindView.class);
//        for(Element element : elements){
//            Element enclosingElement = element.getEnclosingElement();
//            System.out.println("-------------------->" + element.getSimpleName().toString() + "," + enclosingElement.getSimpleName().toString());
//
//        }
        //解析 属性 activity -> List<Element>
        Map<Element, List<Element>> elementsMap = new LinkedHashMap<>();
        for(Element element : elements){
            Element enclosingElement = element.getEnclosingElement();

            List<Element> viewBindElements = elementsMap.get(enclosingElement);
            if(null == viewBindElements){
                viewBindElements = new ArrayList<>();
                elementsMap.put(enclosingElement,viewBindElements);
            }
            viewBindElements.add(element);
        }
        //生成代码
        for(Map.Entry<Element,List<Element>> entry : elementsMap.entrySet()){
            Element enclosingElement  = entry.getKey();
            List<Element> viewBindElements = entry.getValue();


            //生成public final class xxxActivity_ViewBinding implements Unbinder
            String activityClassNameStr = enclosingElement.getSimpleName().toString();
            ClassName activityclssName = ClassName.bestGuess(activityClassNameStr);
            ClassName unbinderClassName = ClassName.get("com.feng.android.butterknife","Unbinder");
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(activityClassNameStr + "_ViewBinding")
                    .addModifiers(Modifier.FINAL,Modifier.PUBLIC)
                    .addSuperinterface(unbinderClassName)
                    .addField(activityclssName,"target",Modifier.PRIVATE);

            //实现unbind 方法
            //androidx.annotation.CallSuper
            ClassName callSuperClassName = ClassName.get("androidx.annotation","CallSuper");
            MethodSpec.Builder unbindMethodBuilder = MethodSpec.methodBuilder("unbind")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                    .addAnnotation(callSuperClassName);

            unbindMethodBuilder.addStatement("$T target = this.target",activityclssName);
            unbindMethodBuilder.addStatement("if (target == null) throw new IllegalStateException(\"Bindings already cleared.\");");

            //构造函数
            MethodSpec.Builder constructorMethodBuilder = MethodSpec.constructorBuilder()
                    .addParameter(activityclssName,"target");
            //this.target = target
            constructorMethodBuilder.addStatement("this.target = target");

            //findViewById 属性
            for(Element viewBindElement : viewBindElements){
                //target.textView1 = Utils.findViewById(source,R.id.tv1)
                String fieldName = viewBindElement.getSimpleName().toString();
                ClassName utilsClassName = ClassName.get("com.feng.android.butterknife","Utils");
                int resId = viewBindElement.getAnnotation(BindView.class).value();
                constructorMethodBuilder.addStatement("target.$L = $T.findViewById(target,$L)",fieldName,utilsClassName,resId);

                //target.textView1 = null;
                unbindMethodBuilder.addStatement("target.$L = null",fieldName);
            }

            classBuilder.addMethod(constructorMethodBuilder.build());
            classBuilder.addMethod(unbindMethodBuilder.build());

            //生成类
            try {
                String packageName = mElementUtils.getPackageOf(enclosingElement).getQualifiedName().toString();
                JavaFile.builder(packageName,classBuilder.build())
                        .addFileComment("butterknife 自动生成")
                        .build().writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("翻车了！");
            }
        }
        return false;
    }
}
