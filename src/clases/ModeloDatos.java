package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import clases.empleado.EmpleadoEventual;
import clases.empleado.EmpleadoPlanilla;
import clases.empleado.Medico;


public class ModeloDatos {
	
	HashMap<String, Paciente> pacientesMap;
	HashMap<String, EmpleadoPlanilla> empleadosPlanillaMap;
	HashMap<String, EmpleadoEventual> empleadosEventualMap;
	HashMap<String, Medico> medicosMap;
	ArrayList<CitaMedica> citasList;
	
	public ModeloDatos() {
		pacientesMap=new HashMap<String, Paciente>();
		empleadosPlanillaMap=new  HashMap<String, EmpleadoPlanilla>();
		medicosMap=new HashMap<String, Medico>();
		empleadosEventualMap=new HashMap<String, EmpleadoEventual>();
		citasList=new ArrayList<CitaMedica>();
	}
	/////
	public void registrarPersona(Paciente miPaciente) {
		String existe=" ";
		for(String paciente : pacientesMap.keySet()) {
			if(paciente.equals(miPaciente.getNumeroDeDNI())) {
				existe="true";
		}
	}
	if (existe.equalsIgnoreCase("true")){
		System.out.println("Ya existe una persona registrada con ese numero de documento");
	}
	else {
		pacientesMap.put(miPaciente.getNumeroDeDNI(), miPaciente);
		System.out.println("Se ha registrado el paciente "+ miPaciente.getNombre() +" Satisfactoriamente");
		}
	}
	//////
	public void registrarPersona(EmpleadoPlanilla miEmpPlanilla) {
		String existe=" ";
		for(String empPlanilla : empleadosPlanillaMap.keySet()) {
			if(empPlanilla.equals(miEmpPlanilla.getNumeroDeDNI())) {
				existe="true";
		}
	}
	if (existe.equalsIgnoreCase("true")){
		System.out.println("Ya existe una persona registrada con ese numero de documento");
	}

	else {
		empleadosPlanillaMap.put(miEmpPlanilla.getNumeroDeDNI(), miEmpPlanilla);
		System.out.println("Se ha registrado el empleado por planilla "+ miEmpPlanilla.getNombre() +" Satisfactoriamente");
	}
	//////
	}
	public void registrarPersona(EmpleadoEventual miEmpEventual) {
		String existe=" ";
		for(String empEventual : empleadosEventualMap.keySet()) {
			if( empEventual.equals(miEmpEventual.getNumeroDeDNI())) {
				existe="true";
		}
	}
	if (existe.equalsIgnoreCase("true")){
		System.out.println("Ya existe una persona registrada con ese numero de documento");
	}
	else {

		empleadosEventualMap.put(miEmpEventual.getNumeroDeDNI(), miEmpEventual);
		System.out.println("Se ha registrado el empleado eventual "+ miEmpEventual.getNombre() +" Satisfactoriamente");
		}
	}
	/////
	public void registrarPersona(Medico miMedico) {
		String existe=" ";
		for(String medico : medicosMap.keySet()) {
			if( medico.equals(miMedico.getNumeroDeDNI())) {
				existe="true";
		}
	}
	if (existe.equalsIgnoreCase("true")){
		System.out.println("Ya existe una persona registrada con ese numero de documento");
	}
	else {
		medicosMap.put(miMedico.getNumeroDeDNI(), miMedico);
		System.out.println("Se ha registrado el medico "+ miMedico.getNombre() +" Satisfactoriamente");
		}
	}
	/////
	public void imprimirPacientes() {
		String msj="\nPACIENTES REGISTRADOS\n";
		Iterator<String> iterator=pacientesMap.keySet().iterator();
		if(pacientesMap.size()>0) {
			
			while (iterator.hasNext()) {
				
				String clave = iterator.next();
				pacientesMap.get(clave).imprimirDatosPersona(msj);
				}
		}else {
			System.out.println("No hay pacientes registrados");
		}
		
	}
	
	
	public void imprimirEmpleadosEventuales() {
		String msj="\nEMPLEADOS EVENTUALES REGISTRADOS\n";
		if(empleadosEventualMap.size()>0) {
			for (String clave : empleadosEventualMap.keySet()) {
				empleadosEventualMap.get(clave).imprimirDatosPersona(msj);
			}
		}else {
			System.out.println("No hay empleados eventuales registrados");
		}
		
	}
	public void imprimirEmpleadosPorPlanilla() {
		String msj="\nEMPLEADOS POR PLANILLA REGISTRADOS\n";
		msj+="***************************\n";
		if(empleadosPlanillaMap.size()>0) {
			for (EmpleadoPlanilla empleadoPlanilla : empleadosPlanillaMap.values()) {
				empleadoPlanilla.imprimirDatosPersona(msj);
			}
		}else {
			System.out.println("No hay empleados por planilla registrados");
		}
		
		msj+="<<<MEDICOS REGISTRADOS>>>\n";
		if(medicosMap.size()>0) {
			for (Map.Entry<String, Medico> elemento : medicosMap.entrySet()){
				elemento.getValue().imprimirDatosPersona(msj);
			}
		}else {
			System.out.println("No hay medicos registrados");
		}
		
	}
	
	public void imprimirMedico() {
		String msj="\nMEDICOS REGISTRADOS\n";
		if(medicosMap.size()>0) {
			for (Map.Entry<String, Medico> elemento : medicosMap.entrySet()){
				elemento.getValue().imprimirDatosPersona(msj);
			}
		}else {
			System.out.println("No hay medicos registrados");
		}
	}
	
	public Medico consultaMedicoPorNombre(String nombreMedico) {
		
		for (Medico medico : medicosMap.values()) {
			
			if (medico.getNombre().equalsIgnoreCase(nombreMedico)) {
				return medico;
			}
		}
		return null;
	}
	
	
	public Paciente consultarPacientePorDocumento(String documentoPaciente) {
		Paciente miPaciente=null;
		
		if (pacientesMap.containsKey(documentoPaciente)) {
			miPaciente=pacientesMap.get(documentoPaciente);
			
		}
		return miPaciente;
	}
	
	public void registrarCitaMedica(CitaMedica miCita) {
		citasList.add(miCita);
		System.out.println("Se ha registrado la cita exitosamente\n");
		System.out.println(miCita.informacionCitaMedica());
	}
	
	public void imprimirCitasMedicasProgramadas() {
		String msj="CITAS MEDICAS PROGAMADAS\n";
		CitaMedica miCita=null;
		
		System.out.println(msj+"\n");
		
		if (citasList.size()>0) {
			for (int i = 0; i < citasList.size(); i++) {
				miCita=citasList.get(i);
				System.out.println(miCita.informacionCitaMedica());
				
			}
		}else {
			System.out.println("No existen citas programadas");
	}
}
	public void imprimirPacientePorDocumento() {
		String numero=JOptionPane.showInputDialog("Ingrese el documento a consultar");
		String msj="\n<<<<<PACIENTE>>>>>\n";
		if(pacientesMap.size()>0) {
			if (pacientesMap.containsKey(numero)) {
				pacientesMap.get(numero).imprimirDatosPersona(msj);
			
			}
		}else {
			System.out.println("No hay pacientes registrados");
			}
		}
	
	public void imprimirEmpEventualesPorDocumento() {
		String numero=JOptionPane.showInputDialog("Ingrese el documento a consultar");
		String msj="\n<<<<<EMPLEADO EVENTUAL>>>>>\n";
		if(pacientesMap.size()>0) {
			if (empleadosEventualMap.containsKey(numero)) {
				empleadosEventualMap.get(numero).imprimirDatosPersona(msj);
				
				}
		}else {
			System.out.println("No hay empleados eventuales registrados");
			}
		
		}
	
	public void imprimirEmpPlanillaPorDocumento() {
		String numero=JOptionPane.showInputDialog("Ingrese el documento a consultar");
		String msj="\n<<<<<EMPLEADO PLANILLA>>>>>\n";
		if(pacientesMap.size()>0) {
			if (empleadosPlanillaMap.containsKey(numero)) {
				empleadosPlanillaMap.get(numero).imprimirDatosPersona(msj);
			}
		}else {
			System.out.println("No hay empleados por planilla registrados");
			}
		}
	
	public void imprimirMedicoPorDocumento() {
		String numero=JOptionPane.showInputDialog("Ingrese el documento a consultar");
		String msj="\n<<<<<MEDICOS>>>>>\n";
		if(pacientesMap.size()>0) {
			if (medicosMap.containsKey(numero)) {
				medicosMap.get(numero).imprimirDatosPersona(msj);
			}
		}else {
			System.out.println("No hay medicos registrados");
			}
		
		}
		
	}
	
	
