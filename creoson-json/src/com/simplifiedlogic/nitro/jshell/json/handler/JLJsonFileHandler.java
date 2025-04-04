/*
 * MIT LICENSE
 * Copyright 2000-2025 Simplified Logic, Inc
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal 
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions: The above copyright 
 * notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", 
 * WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.simplifiedlogic.nitro.jshell.json.handler;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.simplifiedlogic.nitro.jlink.data.AssembleInstructions;
import com.simplifiedlogic.nitro.jlink.data.FileAssembleResults;
import com.simplifiedlogic.nitro.jlink.data.FileInfoResults;
import com.simplifiedlogic.nitro.jlink.data.FileListInstancesResults;
import com.simplifiedlogic.nitro.jlink.data.FileOpenResults;
import com.simplifiedlogic.nitro.jlink.data.JLAccuracy;
import com.simplifiedlogic.nitro.jlink.data.JLConstraintInput;
import com.simplifiedlogic.nitro.jlink.data.JLTransform;
import com.simplifiedlogic.nitro.jlink.data.ListMaterialResults;
import com.simplifiedlogic.nitro.jlink.data.MasspropsData;
import com.simplifiedlogic.nitro.jlink.intf.IJLFile;
import com.simplifiedlogic.nitro.jshell.json.request.JLFileRequestParams;
import com.simplifiedlogic.nitro.jshell.json.response.JLFileResponseParams;
import com.simplifiedlogic.nitro.rpc.JLIException;

/**
 * Handle JSON requests for "file" functions
 * 
 * @author Adam Andrews
 *
 */
public class JLJsonFileHandler extends JLJsonCommandHandler implements JLFileRequestParams, JLFileResponseParams {

	private IJLFile fileHandler = null;

	/**
	 * @param fileHandler
	 */
	public JLJsonFileHandler(IJLFile fileHandler) {
		this.fileHandler = fileHandler;
	}

	/* (non-Javadoc)
	 * @see com.simplifiedlogic.nitro.jshell.json.handler.JLJsonCommandHandler#handleFunction(java.lang.String, java.lang.String, java.util.Hashtable)
	 */
	public Hashtable<String, Object> handleFunction(String sessionId, String function, Hashtable<String, Object> input) throws JLIException {
		if (function==null)
			return null;
		
		if (function.equals(FUNC_OPEN)) return actionOpen(sessionId, input);
		else if (function.equals(FUNC_OPEN_ERRORS)) return actionOpenErrors(sessionId, input);
		else if (function.equals(FUNC_RENAME)) return actionRename(sessionId, input);
		else if (function.equals(FUNC_SAVE)) return actionSave(sessionId, input);
		else if (function.equals(FUNC_BACKUP)) return actionBackup(sessionId, input);
		else if (function.equals(FUNC_ERASE)) return actionErase(sessionId, input);
		else if (function.equals(FUNC_ERASE_NOT_DISP)) return actionEraseNotDisplayed(sessionId, input);
		else if (function.equals(FUNC_REGENERATE)) return actionRegenerate(sessionId, input);
		else if (function.equals(FUNC_REFRESH)) return actionRefresh(sessionId, input);
		else if (function.equals(FUNC_REPAINT)) return actionRepaint(sessionId, input);
		else if (function.equals(FUNC_DISPLAY)) return actionDisplay(sessionId, input);
		else if (function.equals(FUNC_CLOSE_WINDOW)) return actionCloseWindow(sessionId, input);
		else if (function.equals(FUNC_GET_ACTIVE)) return actionGetActive(sessionId, input);
		else if (function.equals(FUNC_IS_ACTIVE)) return actionIsActive(sessionId, input);
		else if (function.equals(FUNC_LIST)) return actionList(sessionId, input);
		else if (function.equals(FUNC_EXISTS)) return actionExists(sessionId, input);
		else if (function.equals(FUNC_GET_FILEINFO)) return actionGetFileinfo(sessionId, input);
		else if (function.equals(FUNC_RELATIONS_GET)) return actionRelationsGet(sessionId, input);
		else if (function.equals(FUNC_RELATIONS_SET)) return actionRelationsSet(sessionId, input);
		else if (function.equals(FUNC_POST_REGEN_RELATIONS_GET)) return actionPostRegenRelationsGet(sessionId, input);
		else if (function.equals(FUNC_POST_REGEN_RELATIONS_SET)) return actionPostRegenRelationsSet(sessionId, input);
		else if (function.equals(FUNC_HAS_INSTANCES)) return actionHasInstances(sessionId, input);
		else if (function.equals(FUNC_LIST_INSTANCES)) return actionListInstances(sessionId, input);
		else if (function.equals(FUNC_MASSPROPS)) return actionMassprops(sessionId, input);
		else if (function.equals(FUNC_GET_LENGTH_UNITS)) return actionGetLengthUnits(sessionId, input);
		else if (function.equals(FUNC_GET_MASS_UNITS)) return actionGetMassUnits(sessionId, input);
		else if (function.equals(FUNC_SET_LENGTH_UNITS)) return actionSetLengthUnits(sessionId, input);
		else if (function.equals(FUNC_GET_UNIT_SYSTEM)) return actionGetUnitSystem(sessionId, input);
		else if (function.equals(FUNC_SET_UNIT_SYSTEM)) return actionSetUnitSystem(sessionId, input);
		else if (function.equals(FUNC_SET_MASS_UNITS)) return actionSetMassUnits(sessionId, input);
		else if (function.equals(FUNC_ASSEMBLE)) return actionAssemble(sessionId, input);
		else if (function.equals(FUNC_GET_TRANSFORM)) return actionGetTransform(sessionId, input);
		else if (function.equals(FUNC_LIST_SIMP_REPS)) return actionListSimpReps(sessionId, input);
		else if (function.equals(FUNC_GET_CUR_MATL)) return actionGetCurrentMaterial(sessionId, input);
		else if (function.equals(FUNC_GET_CUR_MATL_WILDCARD)) return actionGetCurrentMaterialWildcard(sessionId, input);
		else if (function.equals(FUNC_SET_CUR_MATL)) return actionSetCurrentMaterial(sessionId, input);
		else if (function.equals(FUNC_LIST_MATERIALS)) return actionListMaterials(sessionId, input);
		else if (function.equals(FUNC_LIST_MATERIALS_WILDCARD)) return actionListMaterialsWildcard(sessionId, input);
		else if (function.equals(FUNC_LOAD_MATL_FILE)) return actionLoadMaterialFile(sessionId, input);
		else if (function.equals(FUNC_DELETE_MATERIAL)) return actionDeleteMaterial(sessionId, input);
		else if (function.equals(FUNC_GET_ACCURACY)) return actionGetAccuracy(sessionId, input);
		else if (function.equals(FUNC_CREATE_UNIT_SYSTEM)) return actionCreateUnitSystem(sessionId, input);
		else {
			throw new JLIException("Unknown function name: " + function);
		}
	}
	
	private Hashtable<String, Object> actionOpen(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String dirname = checkStringParameter(input, PARAM_DIRNAME, false);
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        Object namesObj = checkParameter(input, PARAM_MODELS, false);
        List<String> filenames = null;
        if (namesObj!=null) {
        	filenames = getStringListValue(namesObj);
        }
        String generic = checkStringParameter(input, PARAM_GENERIC, false);
        boolean display = checkFlagParameter(input, PARAM_DISPLAY, false, false);
        boolean activate = checkFlagParameter(input, PARAM_ACTIVATE, false, false);
        boolean newwin = checkFlagParameter(input, PARAM_NEWWIN, false, false);
        boolean regen_force = checkFlagParameter(input, PARAM_FORCE, false, false);
        
        if (filename==null && filenames==null)
        	throw new JLIException("No filename or filenames parameter given");

        
        FileOpenResults result = fileHandler.open(dirname, filename, filenames, generic, display, activate, newwin, regen_force, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (result!=null) {
        	if (result.getDirname()!=null)
        		out.put(OUTPUT_DIRNAME, result.getDirname());
        	if (result.getFilenames()!=null)
        		out.put(OUTPUT_FILES, result.getFilenames());
        	if (result.getFileRevision()>0)
        		out.put(OUTPUT_REVISION, result.getFileRevision());
        }
    	return out;
	}

	private Hashtable<String, Object> actionOpenErrors(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        boolean errors = fileHandler.openErrors(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        out.put(OUTPUT_ERRORS, errors);
       	return out;
	}

	private Hashtable<String, Object> actionRename(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        String newname = checkStringParameter(input, PARAM_NEWNAME, true);
        boolean onlysession = checkFlagParameter(input, PARAM_ONLYSESSION, false, false);
        
        String newFilename = fileHandler.rename(filename, newname, onlysession, sessionId);
		
		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (newFilename!=null) {
       		out.put(OUTPUT_MODEL, newFilename);
        }
    	return out;
	}

	private Hashtable<String, Object> actionSave(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        List<String> filenames = null;
        Object namesObj = checkParameter(input, PARAM_MODELS, false);
        if (namesObj!=null) {
        	filenames = getStringListValue(namesObj);
        }
        
        fileHandler.save(filename, filenames, sessionId);
		
        return null;
	}

	private Hashtable<String, Object> actionBackup(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, true);
        String targetdir = checkStringParameter(input, PARAM_TARGETDIR, true);
        
        fileHandler.backup(filename, targetdir, sessionId);
		
        return null;
	}

	private Hashtable<String, Object> actionErase(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        List<String> filenames = null;
        Object namesObj = checkParameter(input, PARAM_MODELS, false);
        if (namesObj!=null) {
        	filenames = getStringListValue(namesObj);
        }
        boolean eraseChildren = checkFlagParameter(input, PARAM_ERASE_CHILDREN, false, false);
        
        fileHandler.erase(filename, filenames, eraseChildren, sessionId);
		
        return null;
	}

	private Hashtable<String, Object> actionEraseNotDisplayed(String sessionId, Hashtable<String, Object> input) throws JLIException {
        
        fileHandler.eraseNotDisplayed(sessionId);
		
        return null;
	}

	private Hashtable<String, Object> actionRegenerate(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        List<String> filenames = null;
        Object namesObj = checkParameter(input, PARAM_MODELS, false);
        if (namesObj!=null) {
        	filenames = getStringListValue(namesObj);
        }
        boolean display = checkFlagParameter(input, PARAM_DISPLAY, false, false);
        
        fileHandler.regenerate(filename, filenames, display, sessionId);
		
        return null;
	}

	private Hashtable<String, Object> actionGetActive(String sessionId, Hashtable<String, Object> input) throws JLIException {
        FileOpenResults result = fileHandler.getActive(sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (result!=null && result.hasFile()) {
        	if (result.getDirname()!=null)
        		out.put(OUTPUT_DIRNAME, result.getDirname());
       		out.put(OUTPUT_MODEL, result.getFilenames()[0]);
        }
    	return out;
	}

	private Hashtable<String, Object> actionList(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        List<String> filenames = null;
        Object namesObj = checkParameter(input, PARAM_MODELS, false);
        if (namesObj!=null) {
        	filenames = getStringListValue(namesObj);
        }
        
        List<String> files = fileHandler.list(filename, filenames, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (files!=null) {
       		out.put(OUTPUT_FILES, files);
        }
    	return out;
	}

	private Hashtable<String, Object> actionExists(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, true);
        
        boolean exists = fileHandler.exists(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        out.put(OUTPUT_EXISTS, exists);
       	return out;
	}

	private Hashtable<String, Object> actionGetFileinfo(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        FileInfoResults result = fileHandler.getFileinfo(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (result!=null) {
        	if (result.getDirname()!=null)
        		out.put(OUTPUT_DIRNAME, result.getDirname());
        	if (result.getFilename()!=null)
        		out.put(OUTPUT_MODEL, result.getFilename());
        	if (result.getFileRevision()>0)
        		out.put(OUTPUT_REVISION, result.getFileRevision());
        }
    	return out;
	}

	private Hashtable<String, Object> actionHasInstances(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        boolean exists = fileHandler.hasInstances(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        out.put(OUTPUT_EXISTS, exists);
       	return out;
	}

	private Hashtable<String, Object> actionListInstances(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        FileListInstancesResults result = fileHandler.listInstances(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (result!=null) {
        	if (result.getDirname()!=null)
        		out.put(OUTPUT_DIRNAME, result.getDirname());
        	if (result.getGeneric()!=null)
        		out.put(OUTPUT_GENERIC, result.getGeneric());
        	if (result.getInstances()!=null)
        		out.put(OUTPUT_FILES, result.getInstances());
        	else
        		out.put(OUTPUT_FILES, new Vector<String>());
        }
    	return out;
	}

	private Hashtable<String, Object> actionMassprops(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        MasspropsData result = fileHandler.massprops(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (result!=null) {
			out.put(OUTPUT_VOLUME, result.getVolume());
			out.put(OUTPUT_MASS, result.getMass());
			out.put(OUTPUT_DENSITY, result.getDensity());
			out.put(OUTPUT_SURFACE_AREA, result.getSurfaceArea());
			
			if (result.getCenterGravityInertiaTensor()!=null) {
				Hashtable<String, Object> tmp = writeInertia(result.getCenterGravityInertiaTensor());
				if (tmp!=null)
					out.put(OUTPUT_CTR_GRAV_INERTIA_TENSOR, tmp);
			}
			
			if (result.getCoordSysInertia()!=null) {
				Hashtable<String, Object> tmp = writeInertia(result.getCoordSysInertia());
				if (tmp!=null)
					out.put(OUTPUT_COORD_SYS_INERTIA, tmp);
			}
			
			if (result.getCoordSysInertiaTensor()!=null) {
				Hashtable<String, Object> tmp = writeInertia(result.getCoordSysInertiaTensor());
				if (tmp!=null)
					out.put(OUTPUT_COORD_SYS_INERTIA_TENSOR, tmp);
			}

			if (result.getGravityCenter()!=null) {
				Hashtable<String, Object> tmp = writePoint(result.getGravityCenter());
				if (tmp!=null)
					out.put(OUTPUT_CTR_GRAV, tmp);
			}
        }
    	return out;
	}

	private Hashtable<String, Object> actionGetMassUnits(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        String units = fileHandler.getMassUnits(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (units!=null) {
			out.put(OUTPUT_UNITS, units);
        }
    	return out;
	}

	private Hashtable<String, Object> actionSetMassUnits(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        String units = checkStringParameter(input, PARAM_UNITS, true);
        boolean convert = checkFlagParameter(input, PARAM_CONVERT, false, true);
        
        fileHandler.setMassUnits(filename, units, convert, sessionId);

		return null;
	}

	private Hashtable<String, Object> actionGetLengthUnits(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        String units = fileHandler.getLengthUnits(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (units!=null) {
			out.put(OUTPUT_UNITS, units);
        }
    	return out;
	}

	private Hashtable<String, Object> actionSetLengthUnits(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        String units = checkStringParameter(input, PARAM_UNITS, true);
        boolean convert = checkFlagParameter(input, PARAM_CONVERT, false, true);
        
        fileHandler.setLengthUnits(filename, units, convert, sessionId);

		return null;
	}

	private Hashtable<String, Object> actionGetUnitSystem(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);

        String name = fileHandler.getUnitSystem(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (name!=null) {
			out.put(OUTPUT_NAME, name);
        }
    	return out;
	}

	private Hashtable<String, Object> actionSetUnitSystem(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        String name = checkStringParameter(input, PARAM_NAME, true);
        boolean convert = checkFlagParameter(input, PARAM_CONVERT, false, true);

        fileHandler.setUnitSystem(filename, name, convert, sessionId);

		return null;
	}

	private Hashtable<String, Object> actionRelationsGet(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        List<String> rels = fileHandler.getRelations(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (rels!=null && rels.size()>0) {
	        out.put(OUTPUT_RELATIONS, rels);
        }
        return out;
	}

	private Hashtable<String, Object> actionRelationsSet(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        Object relsObj = checkParameter(input, PARAM_RELATIONS, false);
        List<String> rels = null;
        if (relsObj!=null)
        	rels = getStringListValue(relsObj);
        
        fileHandler.setRelations(filename, rels, sessionId);

        return null;
	}

	private Hashtable<String, Object> actionPostRegenRelationsGet(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        List<String> rels = fileHandler.getPostRegenRelations(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (rels!=null && rels.size()>0) {
	        out.put(OUTPUT_RELATIONS, rels);
        }
        return out;
	}

	private Hashtable<String, Object> actionPostRegenRelationsSet(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        Object relsObj = checkParameter(input, PARAM_RELATIONS, false);
        List<String> rels = null;
        if (relsObj!=null)
        	rels = getStringListValue(relsObj);
        
        fileHandler.setPostRegenRelations(filename, rels, sessionId);

        return null;
	}

	@SuppressWarnings("unchecked")
	private Hashtable<String, Object> actionAssemble(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String dirname = checkStringParameter(input, PARAM_DIRNAME, false);
        String filename = checkStringParameter(input, PARAM_MODEL, true);
        String generic = checkStringParameter(input, PARAM_GENERIC, false);
        String asm = checkStringParameter(input, PARAM_INTOASM, false);
        List<Integer> path = getIntArray(PARAM_PATH, checkParameter(input, PARAM_PATH, false));
        Map<String, Object> transData = checkMapParameter(input, PARAM_TRANSFORM, false);
		JLTransform transform = readTransform(transData);
		Object constraintObj = checkParameter(input, PARAM_CONSTRAINTS, false);
		List<JLConstraintInput> constraints = null;
		if (constraintObj!=null) {
			if (constraintObj instanceof List<?>) {
				constraints = new ArrayList<JLConstraintInput>();
				for (Object o : (List<?>)constraintObj) {
					if (!(o instanceof Map<?, ?>))
						throw new JLIException("Invalid data for constraint: " + o);
					constraints.add(readConstraint((Map<String, Object>)o));
				}
			}
			else if (constraintObj instanceof Map<?, ?>) {
				constraints = new ArrayList<JLConstraintInput>();
				constraints.add(readConstraint((Map<String, Object>)constraintObj));
			}
			else {
				throw new JLIException("Invalid data for constraints: " + constraintObj);
			}
		}
        boolean packageAssembly = checkFlagParameter(input, PARAM_PACKAGE_ASSEMBLY, false, false);
        String refModel = checkStringParameter(input, PARAM_REF_MODEL, false);
        boolean walkChildren = checkFlagParameter(input, PARAM_WALK_CHILDREN, false, false);
        boolean assembleToRoot = checkFlagParameter(input, PARAM_ASSEMBLE_TO_ROOT, false, false);
        boolean suppress = checkFlagParameter(input, PARAM_SUPPRESS, false, false);

        AssembleInstructions instr = new AssembleInstructions();

    	instr.setDirname(dirname);
        instr.setFilename(filename);
        instr.setGenericName(generic);
        instr.setIntoAsm(asm);
        instr.setComponentPath(path);
        instr.setTransData(transform);
        instr.setConstraints(constraints);
        instr.setPackageAssembly(packageAssembly);
        instr.setRefModel(refModel);
        instr.setWalkChildren(walkChildren);
        instr.setAssembleToRoot(assembleToRoot);
        instr.setSuppress(suppress);

        FileAssembleResults result = fileHandler.assemble(instr, sessionId);
        
		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (result!=null) {
        	if (result.getDirname()!=null)
        		out.put(OUTPUT_DIRNAME, result.getDirname());
        	if (result.getFilenames()!=null)
        		out.put(OUTPUT_FILES, result.getFilenames());
        	if (result.getFileRevision()>0)
        		out.put(OUTPUT_REVISION, result.getFileRevision());
        	if (result.getFeatureId()>0)
        		out.put(OUTPUT_FEATUREID, result.getFeatureId());
        }
    	return out;
	}

	private Hashtable<String, Object> actionRefresh(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        fileHandler.refresh(filename, sessionId);
		
        return null;
	}

	private Hashtable<String, Object> actionRepaint(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        fileHandler.repaint(filename, sessionId);
		
        return null;
	}

	private Hashtable<String, Object> actionGetTransform(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String asm = checkStringParameter(input, PARAM_ASM, false);
        List<Integer> path = getIntArray(PARAM_PATH, checkParameter(input, PARAM_PATH, false));
        String csys = checkStringParameter(input, PARAM_CSYS, false);

        JLTransform transform = fileHandler.getTransform(asm, path, csys, sessionId);
        
        if (transform!=null) {
			Hashtable<String, Object> out = writeTransform(transform);
        	return out;
        }
        else {
    		Hashtable<String, Object> out = new Hashtable<String, Object>();
    		return out;
        }
	}

	private Hashtable<String, Object> actionIsActive(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, true);
        
        boolean active = fileHandler.isActive(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        out.put(OUTPUT_ACTIVE, active);
       	return out;
	}

	private Hashtable<String, Object> actionDisplay(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, true);
        boolean activate = checkFlagParameter(input, PARAM_ACTIVATE, false, false);
        
        fileHandler.display(filename, activate, sessionId);
		
        return null;
	}

	private Hashtable<String, Object> actionCloseWindow(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        fileHandler.closeWindow(filename, sessionId);
		
        return null;
	}

	private Hashtable<String, Object> actionListSimpReps(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        String repname = checkStringParameter(input, PARAM_REP, false);
        
        List<String> reps = fileHandler.listSimpReps(filename, repname, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (reps!=null) {
       		out.put(OUTPUT_REPS, reps);
        }
    	return out;
	}

	private Hashtable<String, Object> actionGetCurrentMaterial(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        if (isPattern(filename))
        	throw new JLIException("Not allowed to use wildcards in "+PARAM_MODEL+" argument.");
        
        List<ListMaterialResults> matls = fileHandler.getCurrentMaterial(filename, false, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (matls!=null && matls.size()>0) {
			String matl = matls.get(0).getMaterialName();
			if (matl!=null)
				out.put(OUTPUT_MATERIAL, matl);
        }
    	return out;
	}

	private Hashtable<String, Object> actionGetCurrentMaterialWildcard(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        boolean includeNonMatchingParts = checkFlagParameter(input, PARAM_INCLUDE_NON_MATCHING, false, false);
        
        List<ListMaterialResults> matls = fileHandler.getCurrentMaterial(filename, includeNonMatchingParts, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (matls!=null) {
			Vector<Map<String, Object>> outMatls = new Vector<Map<String, Object>>();
			Map<String, Object> outMatl = null;
			for (ListMaterialResults matl : matls) {
				outMatl = new Hashtable<String, Object>();
				if (matl.getFilename()!=null)
					outMatl.put(PARAM_MODEL, matl.getFilename());
				if (matl.getMaterialName()!=null)
					outMatl.put(PARAM_MATERIAL, matl.getMaterialName());
				
				outMatls.add(outMatl);
			}
			out.put(OUTPUT_MATERIALS, outMatls);
        }
    	return out;
	}

	private Hashtable<String, Object> actionSetCurrentMaterial(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        String materialName = checkStringParameter(input, PARAM_MATERIAL, true);
        
        List<String> files = fileHandler.setCurrentMaterial(filename, materialName, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (files!=null) {
       		out.put(OUTPUT_FILES, files);
        }
    	return out;
	}

	private Hashtable<String, Object> actionListMaterials(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        if (isPattern(filename))
        	throw new JLIException("Not allowed to use wildcards in "+PARAM_MODEL+" argument.");
        String materialName = checkStringParameter(input, PARAM_MATERIAL, false);
        
        List<ListMaterialResults> result = fileHandler.listMaterials(filename, materialName, true, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (result!=null) {
			List<String> matls = new ArrayList<String>();
			for (ListMaterialResults res : result)
				if (res.getMaterialName()!=null)
					matls.add(res.getMaterialName());
			out.put(OUTPUT_MATERIALS, matls);
        }
    	return out;
	}

	private Hashtable<String, Object> actionListMaterialsWildcard(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        String materialName = checkStringParameter(input, PARAM_MATERIAL, false);
        boolean includeNonMatchingParts = checkFlagParameter(input, PARAM_INCLUDE_NON_MATCHING, false, false);
        
        List<ListMaterialResults> matls = fileHandler.listMaterials(filename, materialName, includeNonMatchingParts, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (matls!=null) {
			Vector<Map<String, Object>> outMatls = new Vector<Map<String, Object>>();
			Map<String, Object> outMatl = null;
			for (ListMaterialResults matl : matls) {
				outMatl = new Hashtable<String, Object>();
				if (matl.getFilename()!=null)
					outMatl.put(PARAM_MODEL, matl.getFilename());
				if (matl.getMaterialName()!=null)
					outMatl.put(PARAM_MATERIAL, matl.getMaterialName());
				
				outMatls.add(outMatl);
			}
			out.put(OUTPUT_MATERIALS, outMatls);
        }
    	return out;
	}

	private Hashtable<String, Object> actionLoadMaterialFile(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        String dirname = checkStringParameter(input, PARAM_DIRNAME, false);
        String materialName = checkStringParameter(input, PARAM_MATERIAL, true);
        
        List<String> files = fileHandler.loadMaterialFile(filename, dirname, materialName, false, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (files!=null) {
			out.put(OUTPUT_MODELS, files);
        }
    	return out;
	}

	private Hashtable<String, Object> actionDeleteMaterial(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        String materialName = checkStringParameter(input, PARAM_MATERIAL, true);
        
        List<String> files = fileHandler.deleteMaterial(filename, materialName, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (files!=null) {
			out.put(OUTPUT_MODELS, files);
        }
    	return out;
	}

	private Hashtable<String, Object> actionGetAccuracy(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        
        JLAccuracy accuracy = fileHandler.getAccuracy(filename, sessionId);

		Hashtable<String, Object> out = new Hashtable<String, Object>();
        if (accuracy!=null) {
       		out.put(OUTPUT_ACCURACY, String.valueOf(accuracy.getAccuracy()));
       		out.put(OUTPUT_RELATIVE, String.valueOf(accuracy.isRelative()));
        }
    	return out;
	}

	private Hashtable<String, Object> actionCreateUnitSystem(String sessionId, Hashtable<String, Object> input) throws JLIException {
        String filename = checkStringParameter(input, PARAM_MODEL, false);
        String name = checkStringParameter(input, PARAM_NAME, true);
        String unitLength = checkStringParameter(input, PARAM_UNIT_LENGTH, false);
        String unitMassForce = checkStringParameter(input, PARAM_UNIT_MASS_FORCE, false);
        String unitTime = checkStringParameter(input, PARAM_UNIT_TIME, false);
        String unitTemp = checkStringParameter(input, PARAM_UNIT_TEMP, false);
        boolean mass = checkFlagParameter(input, PARAM_MASS, false, true);

        fileHandler.createUnitSystem(filename, name, mass, unitMassForce, unitLength, unitTime, unitTemp, sessionId);

		return null;
	}

    protected JLConstraintInput readConstraint(Map<String, Object> rec) throws JLIException {
    	if (rec==null)
    		return null;

        JLConstraintInput con = new JLConstraintInput();
        String typeString = checkStringParameter(rec, PARAM_TYPE, true);
        String asmref = checkStringParameter(rec, PARAM_ASMREF, false);
        String compref = checkStringParameter(rec, PARAM_COMPREF, false);
        String asmDatumString = checkStringParameter(rec, PARAM_ASMDATUM, false);
        String compDatumString = checkStringParameter(rec, PARAM_COMPDATUM, false);
        Double offset = checkDoubleParameter(rec, PARAM_OFFSET, false);
        
        con.setType(typeString);
        con.setAsmref(asmref);
        con.setCompref(compref);
        if (asmDatumString!=null) {
	        if (DATUM_SIDE_RED.equalsIgnoreCase(asmDatumString))
	        	con.setAsmDatum(JLConstraintInput.DATUM_SIDE_RED);
	        else if (DATUM_SIDE_YELLOW.equalsIgnoreCase(asmDatumString))
	        	con.setAsmDatum(JLConstraintInput.DATUM_SIDE_YELLOW);
	        else
	        	throw new JLIException("Invalid value for " + PARAM_ASMDATUM + ": " + asmDatumString);
        }
        else
        	con.setAsmDatum(JLConstraintInput.DATUM_SIDE_NONE);
        if (compDatumString!=null) {
	        if (DATUM_SIDE_RED.equalsIgnoreCase(compDatumString))
	        	con.setCompDatum(JLConstraintInput.DATUM_SIDE_RED);
	        else if (DATUM_SIDE_YELLOW.equalsIgnoreCase(compDatumString))
	        	con.setCompDatum(JLConstraintInput.DATUM_SIDE_YELLOW);
	        else
	        	throw new JLIException("Invalid value for " + PARAM_COMPDATUM + ": " + compDatumString);
        }
        else
        	con.setCompDatum(JLConstraintInput.DATUM_SIDE_NONE);
        
        if (offset!=null)
        	con.setOffset(offset.doubleValue());

    	return con;
    }

}