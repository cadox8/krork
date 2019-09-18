/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 *
 */

package net.athonedevs.krork.utils;

import jdk.jfr.Description;
import jdk.jfr.Label;
import jdk.jfr.MetadataDefinition;

import java.lang.annotation.*;

/**
 * Annotation that specifies that an element is experimental and may change
 * without notice.
 * <p>
 * Clients that visualize Flight Recorder events should <em>not</em> show the
 * events or fields annotated with the {@code Experimental} annotation by
 * default. This annotation allows event producers the freedom to try out new
 * events without committing to them.
 * <p>
 * Clients may provide a check box (for example, in a preference page) where a
 * user can opt-in to display experimental data. If the user decide to do so,
 * the user interface should mark experimental events or fields so users can
 * distinguish them from non-experimental events.
 * <p>
 * This annotation is inherited.
 */
@MetadataDefinition
@Label("Experimental")
@Description("Element is not to be shown to a user by default")
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE, ElementType.METHOD })
public @interface Experimental {

}
