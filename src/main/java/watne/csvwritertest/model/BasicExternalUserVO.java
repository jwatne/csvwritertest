package watne.csvwritertest.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * Basic implementation of minimal ExternalUserVO.
 */
public class BasicExternalUserVO implements ExternalUserVO {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "Email")
    private String email;

    public BasicExternalUserVO(final String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BasicExternalUserVO [email=" + email + "]";
    }

}
