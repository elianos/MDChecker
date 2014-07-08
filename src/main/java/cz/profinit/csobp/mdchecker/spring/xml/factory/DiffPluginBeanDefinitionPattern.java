package cz.profinit.csobp.mdchecker.spring.xml.factory;

import cz.profinit.csobp.mdchecker.plugins.DiffPlugin;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * Definice patternu pro DiffPlugin.
 * 
 * Trida parsuje DiffPlugin z xml souboru, definuje inicializaci beany a
 * registruje ji.
 * 
 * @author: vjinoch
 */
public class DiffPluginBeanDefinitionPattern implements BeanDefinitionParser {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.xml.BeanDefinitionParser#parse()
	 */
	public BeanDefinition parse(Element element, ParserContext parserContext) {

		RootBeanDefinition beanDef = new RootBeanDefinition();
		beanDef.setBeanClass(DiffPlugin.class);

		String oldWar = element.getAttribute("oldWar");
		String newWar = element.getAttribute("newWar");

		beanDef.getConstructorArgumentValues().addGenericArgumentValue(oldWar);
		beanDef.getConstructorArgumentValues().addGenericArgumentValue(newWar);

		String id = element.getAttribute("id");
		if (StringUtils.isEmpty(id)) {
			id = "diffPlugin";
		}

		BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDef, id);

		BeanDefinitionReaderUtils.registerBeanDefinition(holder,
				parserContext.getRegistry());

		return beanDef;
	}

}
