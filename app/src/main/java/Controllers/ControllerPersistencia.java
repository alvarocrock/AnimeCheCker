package Controllers;

import com.example.comprobadoranimes.MainActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Constantes.Constantes;
import Models.Capitulo;
import Models.Serie;

/**
 * Controlador que trabajara con archivos xml para controlar la paerssistencia de los datos
 * @author alvar
 *
 */
public class ControllerPersistencia  {

	MainActivity actividad;
	public ControllerPersistencia(MainActivity actividad) {
		this.actividad=actividad;
	}

	/**
	 * Escribe fichero xml de series
	 * @param lista
	 */
	public void guardarseries(ArrayList<Serie> lista) {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			//elemento raiz
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("series");
			doc.appendChild(rootElement);


			for (int cont=0;cont<lista.size();cont++) {
				//primer elemento
				Element elemento1 = doc.createElement("serie");
				rootElement.appendChild(elemento1);

				//elementos
				Element elemento2 = doc.createElement("nombre");
				elemento2.setTextContent(lista.get(cont).getNombre());
				elemento1.appendChild(elemento2);


				Element elemento4 = doc.createElement("referencia");
				elemento4.setTextContent(lista.get(cont).getRef());
				elemento1.appendChild(elemento4);

				Element elemento5 = doc.createElement("estado");
				elemento5.setTextContent(lista.get(cont).getEstado());
				elemento1.appendChild(elemento5);

				Element elemento6 = doc.createElement("imagen");
				elemento6.setTextContent(lista.get(cont).getImagen());
				elemento1.appendChild(elemento6);

				Element elemento7 = doc.createElement("dia");
				elemento7.setTextContent(lista.get(cont).getDia());
				elemento1.appendChild(elemento7);
			}



			//Se escribe el contenido del XML en un archivo
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			//FileOutputStream fos = actividad.openFileOutput("new.xml", actividad.MODE_PRIVATE);
			File file = new File(actividad.getFilesDir(), Constantes.FICHEROSERIES);
			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);

			System.out.print("persistencia guardada en: "+Constantes.FICHEROSERIES+"\n");

		} catch(Exception e){
			e.printStackTrace();
		}


	}

	/**
	 * Escribe fichero xml con los capitulos
	 * @param lista
	 */
	public void guardarcaps(ArrayList<Capitulo> lista) {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			//elemento raiz
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("capitulos");
			doc.appendChild(rootElement);


			for (int cont=0;cont<lista.size();cont++) {
				//primer elemento
				Element elemento1 = doc.createElement("Capitulo");
				rootElement.appendChild(elemento1);

				//elementos
				Element elemento2 = doc.createElement("capitulo");
				elemento2.setTextContent(lista.get(cont).getCapitulo());
				elemento1.appendChild(elemento2);


				Element elemento3 = doc.createElement("serie");
				elemento3.setTextContent(lista.get(cont).getSerie());
				elemento1.appendChild(elemento3);

				Element elemento4 = doc.createElement("referencia");
				elemento4.setTextContent(lista.get(cont).getRef());
				elemento1.appendChild(elemento4);

				Element elemento5 = doc.createElement("imagen");
				elemento5.setTextContent(lista.get(cont).getImagen());
				elemento1.appendChild(elemento5);
			}




			//Se escribe el contenido del XML en un archivo
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File file = new File(actividad.getFilesDir(), Constantes.FICHEROCAPS);
			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);

			System.out.print("persistencia guardada en: "+Constantes.FICHEROCAPS+"\n");

		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Comportamiento para obtener lista  de capitulos capitulos del xml
	 * @return capitulos
	 */
	public ArrayList<Capitulo> obtenercaps(){
		ArrayList<Capitulo> capitulos= new ArrayList<>();
		String capitulo=null;
		String nombre=null;
		String ref=null;
		String img=null;
		int cont=1;
		// Creo una instancia de DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			// Creo un documentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Obtengo el documento, a partir del XML
			File file = new File(actividad.getFilesDir(), Constantes.FICHEROCAPS);
			Document documento = builder.parse(file);

			// Cojo todas las etiquetas serie del documento
			NodeList listaCoches = documento.getElementsByTagName("Capitulo");

			// Recorro las etiquetas
			for (int i = 0; i<listaCoches.getLength(); i++) {
				// Cojo el nodo actual
				Node nodo = listaCoches.item(i);
				// Compruebo si el nodo es un elemento
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					// Lo transformo a Element
					Element e = (Element) nodo;
					// Obtengo sus hijos
					NodeList hijos = e.getChildNodes();
					for (int j = 0; j < hijos.getLength(); j++) {
						// Obtengo al hijo actual
						Node hijo = hijos.item(j);
						// Compruebo si es un nodo
						if (hijo.getNodeType() == Node.ELEMENT_NODE) {
							if (cont==1) {
								capitulo= hijo.getTextContent();
								cont++;
							} else if (cont==2){
								nombre=hijo.getTextContent();
								cont++;
							} else if (cont==3){
								ref=hijo.getTextContent();
								cont++;
							} else{
								img=hijo.getTextContent();
								cont=1;
							}
							if (capitulo!=null && nombre!=null && ref!=null && img!=null) {
								Capitulo cap= new Capitulo(capitulo, nombre,ref,img);
								capitulos.add(cap);
								capitulo=null;
								nombre=null;
								ref=null;
								img=null;
							}
							//System.out.println("Propiedad: " + hijo.getNodeName() + ", Valor: " + hijo.getTextContent());
						}

					}
				}

			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return capitulos;
	}

	/**
	 * Comportamiento para obtener lista con las series del xml
	 * @return series
	 */
	public ArrayList<Serie> obtenerseries(){
		ArrayList<Serie> series= new ArrayList<>();
		String nombre=null;
		String tipo=null;
		String ref=null;
		String estado=null;
		String img=null;
		String dia=null;
		int cont=1;
		// Creo una instancia de DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			// Creo un documentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Obtengo el documento, a partir del XML
			File file = new File(actividad.getFilesDir(), Constantes.FICHEROSERIES);
			Document documento = builder.parse(file);

			// Cojo todas las etiquetas serie del documento
			NodeList listaCoches = documento.getElementsByTagName("serie");

			// Recorro las etiquetas
			for (int i = 0; i<listaCoches.getLength(); i++) {
				// Cojo el nodo actual
				Node nodo = listaCoches.item(i);
				// Compruebo si el nodo es un elemento
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					// Lo transformo a Element
					Element e = (Element) nodo;
					// Obtengo sus hijos
					NodeList hijos = e.getChildNodes();
					for (int j = 0; j < hijos.getLength(); j++) {
						// Obtengo al hijo actual
						Node hijo = hijos.item(j);
						// Compruebo si es un nodo
						if (hijo.getNodeType() == Node.ELEMENT_NODE) {
							if (cont==1) {
								nombre= hijo.getTextContent();
								cont++;
							} else if (cont==2) {
								ref=hijo.getTextContent();
								cont++;
							} else if (cont==3){
								estado=hijo.getTextContent();
								cont++;
							} else if (cont==4){
								img=hijo.getTextContent();
								cont++;
							} else {
								dia=hijo.getTextContent();
								cont=1;
							}
							if (nombre!=null && ref!=null && estado!=null && img !=null && dia!=null) {
								Serie seri= new Serie(nombre,ref,estado,img,dia);
								series.add(seri);
								nombre=null;
								ref=null;
								estado=null;
								img=null;
								dia=null;
							}
							//System.out.println("Propiedad: " + hijo.getNodeName() + ", Valor: " + hijo.getTextContent());
						}

					}
				}

			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return series;
	}
}
