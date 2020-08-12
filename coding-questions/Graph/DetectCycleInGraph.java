class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int[] arr = prerequisites[i];
            if (map.containsKey(arr[0])) {
                map.get(arr[0]).add(arr[1]);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(arr[1]);
                map.put(arr[0], l);
            }
        }
        // visited is to record which node has been deep first checked before
        boolean[] visited = new boolean[numCourses];
        
        // recStack is to record if a node has already been in the path for current deep first check
        boolean[] recStack = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(i, visited, recStack, map)) {
                return false;
            }
        }
        return true;
    }
    public boolean isCyclic(int i, boolean[] visited, boolean[] recStack, HashMap<Integer, List<Integer>> map) {
        if (recStack[i]) {
            return true;
        }
        if (visited[i]) {
            return false;
        }
        visited[i] = true; 
        recStack[i] = true; 
        for (int j = 0; j < map.getOrDefault(i, new ArrayList<>()).size(); j++) {
            if (isCyclic(map.get(i).get(j), visited, recStack, map)) {
                return true;
            }
        }
        recStack[i] = false; // THIS IS QUITE IMPORTANT TO RESET NODE i when the deep first check is done for i. 
  
        return false;
    }
}