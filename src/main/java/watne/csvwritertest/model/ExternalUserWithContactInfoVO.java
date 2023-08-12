package watne.csvwritertest.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * Child class of {@link BasicExternalUserVO} that includes email address.
 */
public class ExternalUserWithContactInfoVO extends BasicExternalUserVO {
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "name")
    private String name;

    public ExternalUserWithContactInfoVO(final String email) {
        super(email);
    }

    public ExternalUserWithContactInfoVO(final String email, final String name) {
        super(email);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExternalUserWithContactInfoVO [email=" + getEmail() + "],[name=" + name + "]";
    }

}
