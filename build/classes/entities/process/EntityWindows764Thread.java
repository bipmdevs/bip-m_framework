/* 
 * Copyright (C) 2016 BIP-M Framework.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package entities.process;

import dump.parsers.process.ParserWin64Process;
import dump.parsers.process.ParserWin64Thread;
import entities.EntityAttribute;
import entities.EntityListWin64;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gonzalo
 */
public class EntityWindows764Thread extends EntityWindowsThread {

    public static final long _SIZE_STRUCTURE = 0x498; //88 elementos, 0x498 bytes 
    public static final long _ENTITY_LIST_OFFSET = 0x420;

    public EntityWindows764Thread() {

        try {
            this.setTag(_TAG);

            this.setAttributes(new HashMap<String, EntityAttribute>());

            /**
             * String _nombre, int _position, String _complexContentType, String
             * _contentType, boolean _union, Entity _entity
             */
            EntityAttribute a0 = new EntityAttribute("KTHREAD", 0x000, "", "KTHREAD", false, new EntityWin764KTHREAD());//TCB Thread Control Block
            this.getAttributes().put("KTHREAD", a0);
            EntityAttribute a1 = new EntityAttribute("CreateTime", 0x360, "windows file time", "int 64", true);
            this.getAttributes().put("CreateTime", a1);
            EntityAttribute a2 = new EntityAttribute("ExitTime", 0x368, "windows file time", "int 64", true);
            this.getAttributes().put("ExitTime", a2);
            /**
             * Los siguientes atributos pertenecen a la estructura _CLIENT_ID
             */
            EntityAttribute a3 = new EntityAttribute("UniqueProcess", 0x3B0, "", "int 64", 1, false);
            this.getAttributes().put("UniqueProcess", a3);
            EntityAttribute a4 = new EntityAttribute("UniqueThread", 0x3B8, "", "int 64", 1, false);
            this.getAttributes().put("UniqueThread", a4);
            /**
             * String _nombre, int _position, String _complexContentType, String
             * _contentType, int _length, boolean _union, int
             * _pointerMultiplicity, boolean _bigEndian, Entity _entity
             */
            EntityAttribute a5 = new EntityAttribute("ThreadListEntry", 0x420, "", "EntityList", 1, false, 1, false, new EntityListWin64()); //Sugiere la misma implementación que para procesos activos
            this.getAttributes().put("ThreadListEntry", a5);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void getAttributesContent(long position, Map.Entry<String, EntityAttribute> e) {
        if (this.getParser() != null) {
            ParserWin64Thread parser = (ParserWin64Thread) this.getParser();
            
            EntityWindows764Thread entityThread = (EntityWindows764Thread) parser.parseEntityByOffset(position, this, e);
            this.setAttributes(entityThread.getAttributes());
            this.setThreadList(entityThread.getThreadList());
            this.setaS(entityThread.getaS());
        }
    }

}
