package egovframework.portal.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

public enum XMLUtil {

	INSTANCE,;

	public static <T> String marshal(T list, String qName, Class<T> clazz) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXBContext jc = JAXBContext.newInstance(clazz);

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

		JAXBElement<T> jaxbElement = new JAXBElement<T>(new QName(qName), clazz, list);
		marshaller.marshal(jaxbElement, sw);

		return sw.toString();
	}

}
