package practica2;

import java.util.InputMismatchException;

import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		boolean salir = false, salir2 = false;
		conexionBD conexion = new conexionBD();
		conexion.login();
		while (!salir) {
			try {
				System.out.print("\n0. SALIR \n" + "1.Gestionar los workers. \n" + "2. Gestionar los departaments. \n"
						+ "\t******ELIGE EL NUMERO QUE DESEE******: ");

				int opcion = entrada.nextInt();
				salir2 = false;
				entrada.nextLine();
				if (opcion == 0) {
					System.out.println("HEMOS SALIDO SATISFACTORIAMENTE");
					salir = true;
				} else if (opcion == 1) {
					while (!salir2) {
						System.out.print(
								"\n0.Volver al menu inicial\n1.Insertar empleado\n2.Modificar empleado\n3.Borrar empleado\n4.Listar empleados\n5.Listar empleados de un departamento\n6.Consultar empleado por DNI\n7.Consultar empleados con salario superior"
										+ "\n8.Consultar empleados con salario igual o inferior a\n\t******ELIGE EL NUMERO QUE DESEE******: ");
						int opcion2 = entrada.nextInt();

						if (opcion2 == 0) {
						} else if (opcion2 == 1) {
							conexion.insertarEmpleado();
						} else if (opcion2 == 2) {

						} else if (opcion2 == 3) {
						} else if (opcion2 == 4) {
						} else if (opcion2 == 5) {
						} else if (opcion2 == 6) {
						} else if (opcion2 == 7) {
						} else if (opcion2 == 8) {
						}
					}
				} else if (opcion == 2) {
					while (!salir2) {
						System.out.print(
								"\n0.Volver al menu inicial\n1.Insertar departamento\n2.Modificar departamento\n3.Eliminar departamento\n4.Listar departamentos\n5.Ver información de un departamento\n\t******ELIGE EL NUMERO QUE DESEE******: ");
						int opcion2 = entrada.nextInt();

						if (opcion2 == 0) {
							salir2 = true;
						} else if (opcion2 == 1) {
						} else if (opcion2 == 2) {
						} else if (opcion2 == 3) {
						} else if (opcion2 == 4) {
						} else if (opcion2 == 5) {
							System.out.println("Por nombre (1) o por DNI (0)");
							int opcion3 = entrada.nextInt();
							if (opcion3 == 1) {

							} else if (opcion3 == 2) {

							}
						}
					}
				} else {
					System.out.println("\nOpcion no contemplada en el menu. ");
				}
			} catch (InputMismatchException e) {
				System.err.println("\nDebes insertar un número\n");
				entrada.next();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		entrada.close();
	}

}
