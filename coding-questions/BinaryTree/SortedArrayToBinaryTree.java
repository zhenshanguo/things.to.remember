/* Convert Sorted Array to Binary Search Tree */

	public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return buildBST(0, nums.length);
    }
    
    TreeNode buildBST(int start, int end) {
        if (start >= end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(start, mid);
        root.right = buildBST(mid + 1, end);
        return root;
    }

    