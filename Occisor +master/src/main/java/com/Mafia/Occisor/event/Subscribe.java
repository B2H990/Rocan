package Maifa.Occisor.event;

import Maifa.Occisor.event.types.EventPriority;
import Maifa.Occisor.event.types.EventType;

import java.lang.annotation.*;
import java.lang.reflect.Method;


@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

    EventPriority priority() default EventPriority.MEDIUM;


    EventType timing() default EventType.PRE;


    boolean persistent() default false;
}
