/** 

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}

*/

package Clases;

public class Main {
    public static void main(String[] args) {
        Usuario admin = new Administrador("Ana", "García", "ana@admin.com", "Av. Siempreviva 123", 1155555555, 150000, 12345678);
        admin.registrarUsuario("12345678");
        ((Administrador) admin).gestionarSistema();

        System.out.println("-----------");

        Usuario comprador = new Comprador("Carlos", "Pérez", "carlos@comprador.com", "Calle Falsa 456", 1166666666, 90000, 23456789);
        comprador.registrarUsuario("23456789");
        ((Comprador) comprador).verPedidos();

        System.out.println("-----------");

        Usuario vendedor = new Vendedor("Valeria", "López", "vale@vendedor.com", "Boulevard 789", 1177777777, 120000, 34567890);
        vendedor.registrarUsuario("34567890");
        ((Vendedor) vendedor).verVehiculosDisponibles();
    }
}
