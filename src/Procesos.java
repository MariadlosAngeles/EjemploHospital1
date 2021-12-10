import javax.swing.JOptionPane;

import clases.CitaMedica;
import clases.ModeloDatos;
import clases.Paciente;
import clases.Persona;
import clases.empleado.EmpleadoEventual;
import clases.empleado.EmpleadoPlanilla;
import clases.empleado.Medico;
public class Procesos extends Persona{
	
	ModeloDatos miModeloDatos;
	
	public Procesos() {
		miModeloDatos=new ModeloDatos();
		presentarMenuOpciones();
		
	}
	
	private void presentarMenuOpciones() {
		
		String menu="MENU HOSPITAL\n";
		menu+="1.Registrar Paciente\n";
		menu+="2.Registrar empleado\n";
		menu+="3.Registrar cita medica\n";
		menu+="4.Imprimir información\n";
		menu+="5.Salir\n\n";
		menu+="Ingrese una opción\n";
		
		do {
			setVerificacion(true);
		
		try {
			int opcion=0;
			
			do {
				opcion=Integer.parseInt(JOptionPane.showInputDialog(menu));
				switch(opcion) {
				case 1:registrarPaciente();
					break;
				case 2:registrarEmpleado();
					break;
				case 3:registrarCitaMedica();
					break;
				case 4:imprimirInformacion();
					break;
				case 5:System.out.println("El sistema ha terminado!");
					break;
				default:System.out.println("No existe el código, verifique nuevamente");
					break;
				
				}
			}while(opcion!=5);

			
		} catch (Exception e) {
		
			JOptionPane.showMessageDialog(null, "Por favor ingrese un tipo de dato numerico");
			setVerificacion(false);
		}
		}while(isVerificacion()!=true);
		
	}
	private void registrarPaciente() {
		Paciente miPaciente=new Paciente();
		miPaciente.registrarDatos();
		
		miModeloDatos.registrarPersona(miPaciente);
	}
	
	private void registrarEmpleado() {
		String menuTipoEmpleado="Registro de Empleado\n";
		menuTipoEmpleado+="1.Empleado Eventual\n";
		menuTipoEmpleado+="2.Empleado por Planilla\n";
		menuTipoEmpleado+="Seleccione el tipo de empleado a registrar\n";
		
		int tipoEmpleado=Integer.parseInt(JOptionPane.showInputDialog(menuTipoEmpleado));
		
		switch (tipoEmpleado) {
		case 1://Registro empleado eventual
			EmpleadoEventual miEmpleadoEventual=new EmpleadoEventual();
			miEmpleadoEventual.registrarDatos();
			miModeloDatos.registrarPersona(miEmpleadoEventual);
			break;
		case 2:
			String resp=JOptionPane.showInputDialog("Ingrese si, si es un medico, de lo contrario es otro tipo de empleado");
			if(resp.equalsIgnoreCase("si")) {
				//Registro Medico
				Medico miMedico=new Medico();
				miMedico.registrarDatos();
				miModeloDatos.registrarPersona(miMedico);
			}else {
				//Registro empleado Planilla
				EmpleadoPlanilla miEmpleadoPlanilla=new EmpleadoPlanilla();
				miEmpleadoPlanilla.registrarDatos();
				miModeloDatos.registrarPersona(miEmpleadoPlanilla);
			}
			break;
			
			default:System.out.println("El tipo de empleado no es valido, intentelo nuevamente");
				break;
		}
	}

	private void registrarCitaMedica() {
		
		
		String documentoPaciente=JOptionPane.showInputDialog("Ingrese el documento del paciente");
		
		Paciente pacienteEncontrado=miModeloDatos.consultarPacientePorDocumento(documentoPaciente);
		
		if(pacienteEncontrado!=null) {
			String nombreMedico=JOptionPane.showInputDialog("Ingrese el nombre del médico");
			Medico medicoEncontrado=miModeloDatos.consultaMedicoPorNombre(nombreMedico);
			
			if(medicoEncontrado!=null) {
				String servicio=JOptionPane.showInputDialog("Ingrese el servicio o motivo de la consulta");
				String fechaConsulta=JOptionPane.showInputDialog("Ingrese la fecha de la consulta");
				String horaConsulta=JOptionPane.showInputDialog("Ingrese la hora de la consulta");
				
				String lugar="La cita en el consultario"+medicoEncontrado.getNumeroDeConsultorio();
				CitaMedica miCita=new CitaMedica(pacienteEncontrado, medicoEncontrado,servicio,fechaConsulta,horaConsulta,lugar);
				miModeloDatos.registrarCitaMedica(miCita);
			}else {
				System.out.println("El medio no se encuentra registrdo en el sistema");
			}
		}else {
			System.out.println("El paciente no se encuentra registrado en el sistema");
		}
	
	}
	
	

	private void imprimirInformacion() {
		String menuImprimir="MENU IMPRESIONES\n";
		menuImprimir+="1.Listar Pacientes\n";
		menuImprimir+="2.Listar Empleado Eventuales\n";
		menuImprimir+="3.Listar Empleados por Planilla\n";
		//menuImprimir+="4.Listar Medicos\n";
		menuImprimir+="4.Listar Citas Programadas\n";
		menuImprimir+="Ingrese una opción\n";
		
		System.out.println("*****************************************************************");
		
		int opcion=Integer.parseInt(JOptionPane.showInputDialog(menuImprimir));
		
		String menuDocumento="1.Registro completo\n";
		menuDocumento+="2.Consultar por documento\n";
		menuDocumento+="Ingrese una opción:";
		
		String menuEmpPlanilla="1.Empleados por Planilla\n";
		menuEmpPlanilla+="2.Medicos\n";
		menuEmpPlanilla+="Ingrese una opción:";
		
		int opcionPlanilla;
		int opcionPersonas;
		
		switch(opcion) {
		case 1:
		    	opcionPersonas=Integer.parseInt(JOptionPane.showInputDialog(menuDocumento));
		    	if(opcionPersonas==1) {
		    		miModeloDatos.imprimirPacientes();
		    	}
		    	else{
		    		miModeloDatos.imprimirPacientePorDocumento();
		    	}
			
			break;
		case 2:
			opcionPersonas=Integer.parseInt(JOptionPane.showInputDialog(menuDocumento));
	    	if(opcionPersonas==1) {
	    		miModeloDatos.imprimirEmpleadosEventuales();
	    	}
	    	else{
	    		miModeloDatos.imprimirEmpEventualesPorDocumento();
	    	}
			break;
		case 3:
			
			opcionPlanilla=Integer.parseInt(JOptionPane.showInputDialog(menuEmpPlanilla));
			switch(opcionPlanilla) {
			case 1:
				opcionPersonas=Integer.parseInt(JOptionPane.showInputDialog(menuDocumento));
		    	if(opcionPersonas==1) {
		    		miModeloDatos.imprimirEmpleadosPorPlanilla();
		    	}
		    	else{
		    		miModeloDatos.imprimirEmpPlanillaPorDocumento();
		    	}
		    	break;
			case 2:
				opcionPersonas=Integer.parseInt(JOptionPane.showInputDialog(menuDocumento));
		    	if(opcionPersonas==1) {
		    		miModeloDatos.imprimirMedico();
		    	}
		    	else{
		    		miModeloDatos.imprimirMedicoPorDocumento();
		    	}
		    	
			}
			break;
		//case 4:miModeloDatos.imprimirMedicos();
			//break;
		case 4:miModeloDatos.imprimirCitasMedicasProgramadas();
			break;
		default:System.out.println("No existe esa opción");
			break;
		
		}
	}
	 
}
