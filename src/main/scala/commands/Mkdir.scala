package commands

import datastructures.List
import filesystem.{ Dir, DirEntry, State }


class Mkdir(name: String) extends Command {
  override def apply(state: State): State = {
    if (!Dir.validateName(name)) {
      state.withMsg("Non valid dir name " + name)
    } else if (state.wd.hasEntry(name)) {
      state.withMsg("Dir " + name + " already exists")
    } else {
      // add implementation
      createDir(state, name)
    }
  }

  def createDir(state:State, name: String): State = {
    def updateStructure(curDir: Dir, path: List[String], newEntry: DirEntry): Dir = ???

    val wd = state.wd
    
    // 1. all the dirs in the full path
    val allDirsInPath = wd.getAllDirNamesInPath

    // 2. create new dir entry in working dir
    val newDir = Dir.empty(wd.path, name)

    // 3. update the whole dir structure from the root
    // the dir structure is IMMUTABLE
    val newRoot = updateStructure(state.root, allDirsInPath, newDir)

    // 4. find new working dir instance given wd's full path in the new dir structure
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }
}
