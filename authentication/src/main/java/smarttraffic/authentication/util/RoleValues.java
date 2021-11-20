package smarttraffic.authentication.util;

public enum RoleValues {
    USER("ROLE_USER"), SYSTEM("SYSTEM"), ADMIN("ROLE_ADMIN");
    private final String value;

    private RoleValues(String value) {
        this.value = value;
    }
}
