package com.own.authority.infrastructure;

import org.hibernate.boot.model.naming.EntityNaming;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

import java.util.Locale;

/**
 * Created by xiemeilong on 15-10-26.
 */
public class UnderlineNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {
    protected static String addUnderscores(String name) {
        StringBuilder buf = new StringBuilder( name.replace('.', '_') );
        for (int i=1; i<buf.length()-1; i++) {
            if (
                    Character.isLowerCase( buf.charAt(i-1) ) &&
                            Character.isUpperCase( buf.charAt(i) ) &&
                            Character.isLowerCase( buf.charAt(i+1) )
                    ) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase(Locale.ROOT);
    }

    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        return toIdentifier(addUnderscores(transformAttributePath(source.getAttributePath())), source.getBuildingContext());
    }

    @Override
    protected String transformEntityName(EntityNaming entityNaming) {
        return addUnderscores(entityNaming.getJpaEntityName());
    }

}
