package watne.csvwritertest.service;

import java.io.StringWriter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import watne.csvwritertest.model.csv.HeaderColumnNameAndOrderMappingStrategy;

/**
 * Service for generating CSV output.
 *
 * @author John Watne
 */
@Service
public class CSVService {
    public <T> String getCsvForItemsInList(final List<T> list, final Class<T> listClass) throws Exception {
        String csvFileContents = null;

        try (StringWriter writer = new StringWriter()) {
            final StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withMappingStrategy(new HeaderColumnNameAndOrderMappingStrategy<T>(listClass))
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR).build();
            beanToCsv.write(list);
            csvFileContents = writer.toString();
        }

        return csvFileContents;
    }
}
