package watne.csvwritertest.model;

/**
 * Child class of {@link BasicExternalUserVO} that includes email address.
 */
public class ExternalUserWithContactInfoVO extends BasicExternalUserVO {
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
