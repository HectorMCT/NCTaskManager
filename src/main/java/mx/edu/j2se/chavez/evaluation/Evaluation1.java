package mx.edu.j2se.chavez.evaluation;

public class Evaluation1 {

    public static void main(String[] args) {

        //Se crean una instancia de la clase Circle de manera erronea
        //Se maneja la excepcion y se muestra un mensaje al usuario sobre el error.
        try{
            Circle circle = new Circle(-10);
        }catch (IllegalArgumentException msgIAE){
            System.out.println(msgIAE.getMessage());
        }

        //Se crea una arreglo de la clase Circle de 3 elementos
        Circle[] circlesArray = new Circle[3];

        //Se crean y guardan en el arreglo 3 instacias de la clase circle
        //Ademas se muestra su radio
        for (int index = 0; index < circlesArray.length; index++){
            Circle auxCircle = new Circle((index + 1) * 10);
            circlesArray[index] = auxCircle;
            System.out.println("Circle: "+ index + " Radio: " + circlesArray[index].getRadius());
        }

        circlesArray[2].setRadius(50.5);

        //Se crea una instacia de la clase circulo utilizando en constructor sin parametros
        //Se guarda en la posicion cero del arreglo
        circlesArray[0] = new Circle();

        int index = 1;
        //Ademas se muestran los radios de los circulos
        for (Circle circle : circlesArray) {
            System.out.println("Circle: "+ index + " Radius: " + circle.getRadius());
            index++;
        }

        //Se imprime el radio del circulo más grande
        System.out.println("El radio del circulo más grade es de: " + circlesArray[biggestCircle(circlesArray)].getRadius() + " unidades.");

    }

    /** Obtine el indice en el arreglo del circulo más grande.
     *
     * */
    public static int biggestCircle(Circle[] circlesArray){

        int indexMaxCircle = 0;

        for(int index = 0; index < circlesArray.length - 1 ;index++ ){
            if(circlesArray[index].getRadius() < circlesArray[index + 1].getRadius()){
                indexMaxCircle = index + 1;
            }
        }
        return indexMaxCircle;
    }
}
