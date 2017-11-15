package com.ahmadrosid.knifelite.knife;

import android.app.Activity;
import android.view.View;

import com.ahmadrosid.knifelite.knife.annotation.KnifeView;
import com.ahmadrosid.knifelite.knife.annotation.KnifeClick;
import com.ahmadrosid.knifelite.knife.annotation.KnifeLongClick;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ocittwo on 11/15/17.
 */

public class Knife {

    private static final String TAG = "Knife";

    public static void bind(final Activity target){
        bindView(target, target.getClass().getDeclaredFields(), target.findViewById(android.R.id.content));
        createOnClick(target, target.getClass().getDeclaredMethods(), target.findViewById(android.R.id.content));
        createOnLongClick(target, target.getClass().getDeclaredMethods(), target.findViewById(android.R.id.content));
    }

    public static void bind(Object obj, View rootView){
        bindView(obj, obj.getClass().getDeclaredFields(), rootView);
        createOnClick(obj, obj.getClass().getDeclaredMethods(), rootView);
        createOnLongClick(obj, obj.getClass().getDeclaredMethods(), rootView);
    }

    private static void createOnLongClick(final Object obj, Method[] methods, View rootView) {
        for (final Method method : methods) {
            Annotation annotation = method.getAnnotation(KnifeLongClick.class);
            if (annotation != null){
                KnifeLongClick knifeLongClick = (KnifeLongClick) annotation;
                int id = knifeLongClick.value();
                View view = rootView.findViewById(id);
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        try {
                            method.setAccessible(true);
                            method.invoke(obj, v);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                });
            }
        }
    }

    private static void createOnClick(final Object obj, Method[] methods, final View rootView) {
        for (final Method method : methods) {
            Annotation annotation = method.getAnnotation(KnifeClick.class);
            if (annotation != null){
                KnifeClick knifeClick = (KnifeClick) annotation;
                int id = knifeClick.value();
                View view = rootView.findViewById(id);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            method.setAccessible(true);
                            method.invoke(obj, v);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    private static void bindView(final Object object, Field[] fields, View rootView){
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(KnifeView.class);
            if (annotation != null){
                KnifeView knifeView = (KnifeView) annotation;
                int id = knifeView.value();
                View view = rootView.findViewById(id);
                try{
                    field.setAccessible(true);
                    field.set(object, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
