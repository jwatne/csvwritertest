package watne.csvwritertest.model.csv;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation for annotating fields as being bound both by column name and order
 * when using OpenCSV library.Adapted*from<a
 * href=https://gist.github.com/ammmze/ec0334d107cb63c586ffd8fc51ec5757>this
 * project</a>.
 **
 * @author jwatne
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface CsvBindByNameOrder {
    String[] value() default {};
}
