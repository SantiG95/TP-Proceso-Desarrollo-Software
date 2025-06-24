package utils;

public class Logger {
    public static boolean MODO_DEBUG = true; 

    private static final String L1 = "  "; 
    private static final String L2 = "    "; 
    private static final String L3 = "      "; 

    public static void system(String message) {
        System.out.println("[SISTEMA] " + message);
    }
    
    public static void chain(String stage) {
        System.out.println("\n" + L1 + ">> Etapa de la Cadena: " + stage.toUpperCase());
    }
    
    public static void handler(String message) {
        System.out.println(L2 + "- " + message);
    }
    
    public static void observerEvent(String event) {
        System.out.println(L2 + "[EVENTO] '" + event + "'. Notificando áreas...");
    }

    public static void observerAction(String observer, String action) {
        System.out.println(L3 + "-> " + observer + ": " + action);
    }
}