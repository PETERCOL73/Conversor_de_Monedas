import com.google.gson.JsonSyntaxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
       Scanner lectura = new Scanner(System.in);
       int opcionElegida = 0;

       // Esta instancia se utilizará para realizar consultas de conversiones de moneda
        ConsultaConversion consulta = new ConsultaConversion();
        /* La razón de pasarle como parámetro una instancia de 'ConsultaConversion' al constructor de 'Calculos' es porque la clase
        'Calculos' necesita tener acceso a una instancia de ConsultaConversion para poder realizar las conversiones de moneda. */
        Calculos calculos = new Calculos (consulta);
        GeneradorDeArchivos generador = new GeneradorDeArchivos();

        List<String> respuestas = new ArrayList<>();

        String menu = """
                \n****************************************************
                *** SEA BIENVENIODO (A) AL CONVERSOR DE MONEDAS ***
                
                1) Dólar >>> Peso Argentino
                2) Peso Argentino >>> Dólar
                3) Dólar >>> Real Brasileño
                4) Real Brasileño >>> Dólar
                5) Dólar >>> Peso Colombiano
                6) Peso Colombiano >>> Dólar
                7) Salir
                *******************************************************
                """;

        while (opcionElegida !=7){
            try {
                System.out.println(menu);
                opcionElegida = Integer.parseInt(lectura.nextLine());

                //Obteción de la marca de tiempo actual
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                switch (opcionElegida) {
                        case 1:
                            calculos.almacenarValores("USD", "ARS");
                            respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                            break;
                        case 2:
                            calculos.almacenarValores("ARS", "USD");
                            respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                            break;
                        case 3:
                            calculos.almacenarValores("USD", "BRL");
                            respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                            break;
                        case 4:
                            calculos.almacenarValores("BRL", "USD");
                            respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                            break;
                        case 5:
                            calculos.almacenarValores("USD", "COL");
                            respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                            break;
                        case 6:
                            calculos.almacenarValores("COL", "USD");
                            respuestas.add(formattedDate + " - " + calculos.obtenerMensajeRespuesta());
                            break;
                        case 7:
                            break;
                        default:
                            System.out.println("Ingrese una opción válida");

                    }
                }   catch (NumberFormatException | InputMismatchException e){
                    System.out.println("Error.  Ingrese un valor numérico válido.");
                }
            }
            generador.guardarJson(respuestas);

            System.out.println("Finalizando programa");


    }
}