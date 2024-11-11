import java.util.Scanner;

/* Es responsable de manejar la lógica relacionada con las conversiones de moneda. Sin embargo, necesita la
información de las tasas de cambio obtenidas a través de ConsultaConversion para realizar estas conversiones. */
public class Calculos {
    private String monedaBase;
    private String monedaObjetivo;
    private double cantidad;
    private ConsultaConversion conversion;

    Scanner lectura = new Scanner(System.in);

     /* Toma una instancia de ConsultaConversion como parámetro y la asigna a una variable de instancia llamada
    conversion. De esta manera, cada objeto de Calculos tiene acceso a una instancia de ConsultaConversion, lo
    que le permite realizar consultas de conversiones de moneda cuando sea necesario. */
    public <ConsultaCoversion> Calculos (ConsultaCoversion conversion){
        this.conversion = (ConsultaConversion) conversion;
    }

    public String getMonedaBase(){
        return monedaBase;
    }

    public String getMonedaObjetivo(){
        return monedaObjetivo;
    }

    public double getCantidad(){
        return cantidad;
    }

    public String almacenarValores(String monedaBase, String monedaObjetivo) {
        this.monedaBase = monedaBase;
        this.monedaObjetivo = monedaObjetivo;
        boolean entradaValida = false;


        do {
            System.out.println("Ingrese el valor que desea convertir");
            if (lectura.hasNextDouble()) {
                this.cantidad = lectura.nextDouble();
                switch (lectura.nextLine()) {
                }
                entradaValida = true;
            } else {
                System.out.println("Error.  Ingrese un valor numérico.");
                lectura.next();
            }
        } while (!entradaValida);
        return monedaBase;
    }

    public String obtenerMensajeRespuesta() {
            String mensaje = "%s %s equivale a: %s %s".formatted(getMonedaBase().toUpperCase(), getCantidad(), getMonedaObjetivo().toUpperCase(), conversion.buscaConversion(getMonedaBase(), getMonedaObjetivo(), getCantidad()));
            System.out.println(mensaje);

        return mensaje;
    }

}