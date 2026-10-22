package app.ui;

import app.service.validators.DataTypeValidator;
import app.view.SeatView;
import app.view.UserView;

public class CliUserInterface {

    private final UserView userView;
    private final SeatView seatView;

    public CliUserInterface(UserView userView , SeatView seatView){
        this.userView = userView;
        this.seatView = seatView;
    }

    public void applicationInit(){

        System.out.println("Bienvenido TicketMax V1");

        int init = DataTypeValidator.validateInt("Presione 1 para iniciar la aplicación");

        while(init != 0){

            int option = DataTypeValidator.validateInt("1. Registro " +
                    "2. Login" +
                    "3. Salir");

            switch (option){
                case 1:
                    userView.createUser();
                    break;
                case 2:
                    System.out.println("Login");
                    menuUser();
                    break;
                case 3:
                    System.out.println("Saliendo de la aplicación");
                    init = 0;
                    break;
                default:
                    System.out.println("Seleccione una opción valida");
                    break;
            }

        }
    }

    public void menuUser(){
        int option;

        do {
            option = DataTypeValidator.validateInt("Seleccione:\n1. Registrar usuario\n2. Consultar usuario por id\n3. Consultar todos los usuarios\n4. Actualizar usuario\n5. Eliminar usuario\n6. Contar usuarios\n0. Salir");

            switch (option){
                case 1:
                    System.out.println("Registrar Usuario");
                    userView.createUser();
                    break;
                case 2:
                    System.out.println("Consultar usuario por id");
                    int id = DataTypeValidator.validateInt("Ingrese el id del usuario a consultar");
                    userView.selectUserById(id);
                    break;
                case 3:
                    System.out.println("Consultar todos los usuarios");
                    userView.selectUsers();
                    break;
                case 4:
                    System.out.println("Actualizar usuario");
                    userView.updateUser();
                    break;
                case 5:
                    System.out.println("Eliminar usuario");
                    int deleteId = DataTypeValidator.validateInt("Ingrese el id del usuario a eliminar");
                    userView.deleteUser(deleteId);
                    break;
                case 6:
                    System.out.println("Contar usuarios");
                    userView.countUsers();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de usuario");
                    break;
                default:
                    System.out.println("Ingrese una opción valida");
            }
        } while (option != 0);
    }

    public void userMenu(){
        menuUser();
    }

}
