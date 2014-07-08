package cz.profinit.csobp.mdchecker.spring;

import cz.profinit.csobp.mdchecker.spring.xml.factory.DiffPluginBeanDefinitionPattern;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Implementace handleru pro namespace MDCheckeru.
 * 
 * Obsahuje reference mezi nazvy bean a jejich definicemi.
 * 
 * @author: vjinoch
 */
public class MDCheckerNamespaceHandler extends NamespaceHandlerSupport {


    /* (non-Javadoc)
     * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
     */
    public void init() {
    	registerBeanDefinitionParser("diffplugin", new DiffPluginBeanDefinitionPattern());
    }
}
