package org.jboss.as.console.client.forms.modelAdapter;

import org.jboss.as.console.client.widgets.forms.Address;
import org.jboss.as.console.client.widgets.forms.Binding;
import org.jboss.as.console.rebind.forms.AddressDeclaration;
import org.jboss.as.console.rebind.forms.ApplicationMetaDataGenerator;
import org.jboss.as.console.rebind.forms.ApplicationMetaDataGenerator.PropBindingDeclarations;
import org.junit.Test;

import java.util.List;

/**
 * @author Heiko Braun
 * @date 9/23/11
 */
public class TestAddressParser {

    @Test
    public void testAnnotationParsing() throws Exception {
        List<PropBindingDeclarations> bindings = ApplicationMetaDataGenerator.mapProperties(ExampleEntity.class);
        AddressDeclaration addressDeclaration = ApplicationMetaDataGenerator.parseAddress(ExampleEntity.class);

        //

    }

    @Address("parent=parent-name/child=*")
    interface ExampleEntity {

        @Binding(detypedName = "entity-name")
        String getName();
        void setName(String name);

        @Binding(detypedName = "valid")
        boolean isValid();
        void setValid(boolean b);

        @Binding(detypedName = "num-requests")
        long getNumRequests();
        void setNumRequests(long requests);

    }
}
