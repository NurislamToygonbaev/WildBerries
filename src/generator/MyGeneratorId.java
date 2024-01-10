package generator;

public class MyGeneratorId {
    private static Long idUser = 0L;
    private static Long idAnnouncement = 0L;

    public static Long getIdUser(){
        return (++idUser);
    }

    public static Long getIdAnnouncement(){
        return (++idAnnouncement);
    }
}
