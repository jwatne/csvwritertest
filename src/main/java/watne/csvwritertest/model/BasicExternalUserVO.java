package watne.csvwritertest.model;

/**
 * Basic implementation of minimal ExternalUserVO.
 */
public class BasicExternalUserVO implements ExternalUserVO {
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
