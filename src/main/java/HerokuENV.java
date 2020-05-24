public class HerokuENV {
    public static String JDBC_URL(){
        String url = System.getenv("JDBC_DATABASE_URL");
        if (url == null) throw new IllegalArgumentException("JDBC_DATABASE_URL is empty!!!");
        return url;

    }
    public static String JDBC_USERNAME() {
        String url = System.getenv("JDBC_DATABASE_USERNAME");
        if (url == null) throw new IllegalArgumentException("JDBC_DATABASE_USERNAME is empty!!!");
        return url;
    }

    public static String JDBC_PASSWORD() {
        String url = System.getenv("JDBC_DATABASE_PASSWORD");
        if (url == null) throw new IllegalArgumentException("JDBC_DATABASE_PASSWORD is empty!!!");
        return url;
    }
    public static int PORT() {
        try {
            return Integer.parseInt(System.getenv("PORT"));
        } catch (NumberFormatException ex) {
            return 5000;
        }
    }
}
