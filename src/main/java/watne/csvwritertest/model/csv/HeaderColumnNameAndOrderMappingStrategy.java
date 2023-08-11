package watne.csvwritertest.model.csv;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.comparators.ComparableComparator;
import org.apache.commons.collections4.comparators.ComparatorChain;
import org.apache.commons.collections4.comparators.FixedOrderComparator;
import org.apache.commons.collections4.comparators.NullComparator;
import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.BeanField;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvBadConverterException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Customization of {@link HeaderColumnNameMappingStrategy} that allows mapping
 * by both column order and name. Adapted
 * from <a
 * href=https://gist.github.com/ammmze/ec0334d107cb63c586ffd8fc51ec5757>this
 * project</a>.
 * <p>
 * NOTE: Skip source project's override of determineConverter; we don't use the
 * custom data types in the example.
 * </p>
 *
 * @author jwatne
 *
 * @param <T>
 *            a Class to which this mapping strategy is applied.
 */
public class HeaderColumnNameAndOrderMappingStrategy<T> extends HeaderColumnNameMappingStrategy<T> {
    /**
     * Constructs the mapping strategy for the given type.
     *
     * @param type
     *             the Class to which the instance of the mapping strategy will be
     *             applied.
     */
    public HeaderColumnNameAndOrderMappingStrategy(final Class<T> type) {
        setType(type);
    }

    @Override
    public String[] generateHeader(final T bean) throws CsvRequiredFieldEmptyException {
        // Overriding this method allows preserving the header column name casing.
        String[] header = super.generateHeader(bean);
        final int numColumns = headerIndex.findMaxIndex();

        if (numColumns != -1) {
            header = new String[numColumns + 1];
            BeanField<T, String> beanField;

            for (int i = 0; i <= numColumns; i++) {
                beanField = findField(i);
                final String columnHeaderName = extractHeaderName(beanField);
                header[i] = columnHeaderName;
            }
        }

        return header;
    }

    @Override
    protected void loadFieldMap() throws CsvBadConverterException {
        // Overriding this method to support setting column order by the custom
        // "CsvBindByNameOrder" annotation.
        if (writeOrder == null && type.isAnnotationPresent(CsvBindByNameOrder.class)) {
            final List<String> predefinedList = Arrays.stream(type.getAnnotation(CsvBindByNameOrder.class).value())
                    .map(String::toUpperCase).collect(Collectors.toList());
            final FixedOrderComparator<String> fixedComparator = new FixedOrderComparator<>(predefinedList);
            fixedComparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.AFTER);
            final Comparator<String> comparator = new ComparatorChain<>(
                    Arrays.asList(fixedComparator, new NullComparator<>(false), new ComparableComparator<>()));
            setColumnOrderOnWrite(comparator);
        }

        super.loadFieldMap();
    }

    /**
     * Extract the annotated header name from the given BeanField.
     *
     * @param beanField
     *                  the attribute whose header String is to be returned.
     * @return the annotated header name from the given BeanField.
     */
    private String extractHeaderName(final BeanField<T, String> beanField) {
        String headerName = StringUtils.EMPTY;

        if (beanField != null) {
            final Field field = beanField.getField();

            if (field != null) {
                if (field.isAnnotationPresent(CsvBindByName.class)) {
                    headerName = field.getDeclaredAnnotationsByType(CsvBindByName.class)[0].column();
                } else if (field.isAnnotationPresent(CsvCustomBindByName.class)) {
                    headerName = field.getDeclaredAnnotationsByType(CsvCustomBindByName.class)[0].column();
                }
            }
        }

        return headerName;
    }
}
