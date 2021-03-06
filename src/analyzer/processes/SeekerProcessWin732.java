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
package analyzer.processes;

import analyzer.interfaces.IAnalyzerThread;
import analyzer.states.ActiveProcessWin732;
import analyzer.states.HiddenProcessWin732;
import analyzer.states.ProcessState;
import analyzer.states.SeekerState;
import analyzer.states.TerminatedProcessWin732;
import entities.Entity;
import entities.process.EntityProcess;
import entities.process.EntityThread;
import java.util.ArrayList;
import system.utils.Observer;

/**
 *
 * @author Gonzalo
 */
public class SeekerProcessWin732 extends SeekerProcess implements IAnalyzerThread {

    public SeekerProcessWin732(ProcessState state) {
        super.setProcessState(state);
    }

    @Override
    public synchronized int countObservers() {
        return super.countObservers(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized boolean hasChanged() {
        return super.hasChanged(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected synchronized void clearChanged() {
        super.clearChanged(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers(Object o) {
        super.notifyObservers(o); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void deleteObserver(Observer obsrvr) {
        super.deleteObserver(obsrvr); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void addObserver(Observer obsrvr) {
        super.addObserver(obsrvr); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected EntityProcess getFirstProcess(ProcessState s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected EntityProcess getNextProcess(EntityProcess process, ProcessState s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected EntityProcess getPrevProcess(EntityProcess process, ProcessState s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity getEntityByAttributeValue(Entity entity, String attribute, Object content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SeekerState obtainPosibleSeekerState(String modifier) {
        SeekerState state = null;

        try {

            if (modifier == null) {
                modifier = "";
            }

            switch (modifier) {
                case "active":
                    state = new ActiveProcessWin732(this.getAService());
                    break;
                case "hidden":
                    state = new HiddenProcessWin732(this.getAService());
                    break;
                case "terminated":
                    state = new TerminatedProcessWin732(this.getAService());
                    break;
                default:
                    state = new ActiveProcessWin732(this.getAService());
                    break;
            }

            this.setProcessState((ProcessState) state);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return state;
    }

    @Override
    public ArrayList<EntityThread> getThreadsByProcess(EntityProcess process) {
        ArrayList<EntityThread> list = new ArrayList<EntityThread>();

        try {

            ArrayList<EntityThread> threadList = this.getProcessState().getThreadListByProcess(process);

            for (EntityThread t : threadList) {
                list.add(t);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

}
