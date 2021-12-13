package wanzhuansuanfamianshi.chapter3;

import java.util.*;

public class Tools3 {
    // 1. 给定一个数组nums，写一个函数，将数组中所有的0挪到数组的末尾，而维持其他所有非0元素的相对位置。
    public static void tool1(int[] arr) {
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != 0) {
                if (j != i) {
                    arr[i] = arr[j];
                    arr[j] = 0;
                }
                i++;
            }
        }
//        for(int k=i;k<arr.length;k++){
//            arr[k]=0;
//        }
        System.out.println(Arrays.toString(arr));
    }

    // 时间复杂度 O(n) , 空间复杂度: O(1)
    // 2. 给定一个有n个元素的数组，数组中元素的取值只有 0，1，2 三种可能。为这个数组进行排序。
    public static void tool2(int[] arr) {
        // 基于计数排序的思想，首先统计 0，1，2 元素的个数，然后将其放到对应的位置。
        int[] count = {0, 0, 0};
        for (int i = 0; i < arr.length; i++) {
            assert arr[i] >= 0 && arr[i] <= 2 : "数组中的元素不符合要求"; // assert false : "异常信息";
            count[arr[i]]++;
        }
        for (int i = 0; i < count[0]; i++) {
            arr[i] = 0;
        }
        for (int i = count[0]; i < count[0] + count[1]; i++) {
            arr[i] = 1;
        }
        for (int i = count[0] + count[1]; i < count[0] + count[1] + count[2]; i++) {
            arr[i] = 2;
        }
        System.out.println("arr: ");
        System.out.println(Arrays.toString(arr));
    }

    // 时间复杂度 O(n) , 空间复杂度: O(1)
    public static void tool2_1(int[] arr) {
        // 基于计数排序的思想，首先统计 0，1，2 元素的个数，然后将其放到对应的位置。
        // tool2 扫描了一遍arr，然后赋值了一遍 arr，遍历了两遍arr
        // 使用三路快排的思想，在遍历的同时进行赋值，只需要遍历一遍arr
        int index0 = -1;// 最后一个 0 的索引
        int index2 = arr.length; // 最后一个 2 的索引
        for (int i = 0; i < index2; ) {
            assert arr[i] >= 0 && arr[i] <= 2 : "数组中的元素不符合要求"; // assert false : "异常信息";
            if (arr[i] == 0) {
                index0++;
                arr[i] = arr[index0];// 注意此处arr[index0] 可能为0 也可能为1， 例如：i=0 的时候。
                arr[index0] = 0;// 因为arr[i] 已经确定为0了，所以此处可以直接使用0。
                // 因为 index0++ 后指向的位置处的元素是已经被 i 访问过的位置，所以将该元素赋值给arr[i]后，可以i++，访问下一个元素。
                // 而 index2-- 后指向的位置是 i 没有访问过的元素，当 将该元素赋值到 i 处后，i不能 ++
                i++;
            } else if (arr[i] == 2) {
                index2--;
                arr[i] = arr[index2];
                arr[index2] = 2;
            } else {
                i++;
            }
        }
        System.out.println("arr: ");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 3. 给定一个有序整型数组和一个整数target，在其中寻找两个元素，使其和为target。 返回这两个数的索引。
     * - 如numbers =[2,7,11,15], target=9
     * - 返回数字 2，7 的索引 1，2（索引从1开始计算)
     */
    public static void tool3(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        String result = "";
        while (left < right) {
            int temp = arr[left] + arr[right];
            if (temp == target) {
                result = result + arr[left] + "-" + arr[right] + ";";
                left++;
                right--;
            } else if (temp < target) {
                left++;
            } else {
                right--;
            }
        }
        if (result.length() <= 0) {
            System.out.println("result: " + result + " 无解");
        } else {
            System.out.println("result: " + result);
        }

    }

    /**
     * 1. 给定一个整型数组和一个数字s，找到数组中最短的一个连续子数组，使得连续子数组中的数字和sum >= s, 返回这个最短的连续子数组的长度值。
     * 如： 给定数组 [2,3,1,2,4,3] , s=7
     * - 答案为[4,3] ,返回 2.
     */
    // 时间复杂度 O(n) ，空间复杂度 O(1)
    public static void tool4(int[] arr, int s) {
// [left,right] 为我们的滑动窗口，
// 也可以设置right=0，不过此时 sum+=arr[++right]将变为 sum+=arr[right++],而且需要考虑只有一个元素的特殊情况，因为只有一个元素时，right+1 =1 ,通不过if条件，而这一个元素可能等于 s
        int left = 0, right = -1;
        int result = arr.length + 1;
        int sum = 0;
        while (left + 1 < arr.length) {
            if (right + 1 < arr.length && sum < s) {
                sum += arr[++right];
            } else {
                /*
                  left + 1 < arr.length && sum >= s 不需要再使用 if 添加这两个条件。
                    1. left + 1 <arr.length  . 当 right 指向最后一个元素，且最后一个元素大于 s，那么left会不断 ++ ， left加到arr，length 时，将过不了while循环，
                    2. sum >=s  当 sum >= s 时必需要执行 下面的代码这样才能减小sum的值。 注意 上面式 if，这里是 else， 所以只会执行一个分支，不会出现 上面sum增加后到达s，然后
                    又在这里被减小的情况。
                 */
                sum -= arr[left++];
            }
            if (sum == s) {
                result = Math.min(result, (right - left + 1));
            }
        }

        if (result != arr.length + 1) {
            System.out.println("arr.length: " + arr.length + " ;  result: " + result);
        } else {
            System.out.println("无解: ");
        }
    }

    /**
     * 5. 在一个字符串中寻找没有重复字母的最长字串
     * 1. 如: "abcabcbb" ，结果为 ”abc"
     * 2. 如："bbbb", 结果为 "b"
     * 3. 如: "pwwkew"， 结果为"wke"
     */
    public static void tool5(String str) {
        int[] freq = new int[256];
        int left = 0, right = -1;//滑动窗口 [left,right]
        int result = 0;
        char[] chars = str.toCharArray(); // char 可以转为 int，因此将 字符作为索引，取freq中查找 该字符的频率
        while (left < chars.length) {
            if (right + 1 < chars.length && freq[chars[right + 1]] == 0) {
                freq[chars[++right]]++; // 将该字符对应的频率 加 1
            } else {
              /*  if(right-left>result){    因为 无论是right变化后，还是left变化后，都需要决定是否更新result
                    result=right-left;
                }*/
                freq[chars[left++]]--;
            }
            // 当 right+1指向的位置重复时，说明经过此次循环后 result 不会改变，而且要开始减小 left。 注意 right还没有改变
            /*、
                当 right+1 指向的位置不重复时，需要 right++， result 也要更新。
                当right+1 指向的位置重复时，result不再更新，left值要不断减减。
              注意： result为最长不重复字串的长度。
             */
            result = Math.max(result, right - left + 1);
        }
        System.out.println(" result: " + result);
    }

    /**
     * 给定两个数组nums，求两个数组的公共元素
     * - 如 nums1 =[1,2,2,1], nums2 =[2,2]
     * - 结果为[2]
     * - 结果中每个元素只能出现一次
     * - 如果有多个公共元素，那么这些公共元素的出现顺序可以是任意的。
     */
    public static void tool6(int[] arr1, int[] arr2) {
        Set<Integer> common = new HashSet<>();
        Set<Integer> record = new HashSet<>();
        for (int i : arr1) {
            record.add(i);
        }
        for (int i : arr2) {
            if (record.contains(i)) {
                common.add(i);
            }
        }
        System.out.println(common);
    }

    public static void tool7(int[] arr1, int[] arr2) {
        Map<Integer, Integer> record = new HashMap<>();
        List<Integer> common = new ArrayList<>();
        for (int i : arr1) {
            if (record.containsKey(i)) {
                int value = record.get(i);
                value++;
                record.put(i, value);
            } else {
                record.put(i, 1);
            }
        }
        for (int i : arr2) {
            if (record.containsKey(i)) {
                int value = record.get(i);
                if (value > 0) {
                    common.add(i);
                    value--;
                    record.put(i, value);
                } else {
                    record.remove(i);
                }
            }
        }
        System.out.println(common);
    }

    public static void tool8(int[] arr, int target) {
        Map<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            if (record.containsKey(target - current)) {
                System.out.println(record.get(target - current) + "  ---  " + i);
                return;
            }
            record.put(current, i);
        }
    }

    public static void tool9(int[] arr, int k) {
        List<Integer> integers = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            if (integers.contains(arr[i])) { // 为了保证arr[i] 属于 这k+1个元素，所以 integers的长度最大为k
                System.out.println(arr[i]);
                return;
            }
            integers.add(arr[i]);
            if (integers.size() > k) {
                integers.remove(0);// 此处的  0 为 index
            }
        }
    }

    public static void tool10(int[] arr, int t, int k) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < arr.length; i++) {
            // treeSet中的最小值 小于等于 arr[i]+t,  treeSet中的最大值 大于等于 arr[i] -t.
            // 这就保证 treeSet中的元素 一定 和 arr[i] -t   和 arr[i] +t 区间有交集。
            if (treeSet.first() <= arr[i] + t && treeSet.last() >= arr[i] - t) { // 保证 有 差值不超过t 的元素
                System.out.println(true);
                return;
            }
            treeSet.add(arr[i]);
            if (treeSet.size() > k) { // 保证 索引之间差值不超过 k
                treeSet.remove(arr[i - k]);// 此处remove传入的参数是对象
            }
        }
    }

    public static void tool11(int n) {
        // 使用 Dequen 是为了使用广度优先遍历 。Inner中第一个Integer表示具体为第几个数字， 第二个Integer表示我们之后建立的图中，经过几段路径走到了该位置。
        /**
         * 1. 给定一个正整数n，寻找最少的完全平方数，使得他们的和为n。
         * 		1. 完全平方数: 1，4，9，16
         * 		2. 12=4+4+4
         * 		3. 13=4+9
         */
        assert n >= 0 : " n 必需是一个正整数 ";
        boolean[] signs = new boolean[n + 1]; // 用于标记图中的节点是否已经被访问过了。
        Deque<Inner> q = new ArrayDeque<>();
        q.push(new Inner(n, 0)); // 对于 n这个数字，一步都不用走便到达了 n
        signs[n] = true; // 标记 节点 n，被访问了。
        while (!q.isEmpty()) {
            int num = q.getFirst().getNum();// 得到 队列中第一个元素对应的 数值，即num
            int count = q.getFirst().getCount();// 得到到达num要经过几步。
            q.pop();// 第一个元素出队列。

            // 相当于 广度优先遍历。但是由于 在图中，要达到一个节点可以有多个路径，所以队列中会有冗余的节点。因此需要引入一个用于标记 节点是否已经被访问过了的符号变量
            // 在使用符号变量后，整个代码才变成真正的在图中进行的广度优先遍历。
            // 如果节点已经被访问过则，
            for (int i = 1; ; i++) {
                //以 num为5为例，产生两种情况 5=》第一种情况 1*1 +2*2【1*1+1*1】  长度为 3，  ，第二种情况 2*2 + 1*1 ，长度为 2
                // 以不同的  i*i 开头，将会导致 num 被分解成平方和后，所得的结果的长度不同。
                int cond = num - i * i;
                if (cond < 0) {
                    break;
                }
                if (cond == 0) { // 注意这是广度优先遍历，不是深度优先遍历。
                    count++;// count ++ ，是因为 num 被分解了一次，num消失，产生 i*i 和 num - i*i ， 长度由 length -》 length+1
                    System.out.println("num: " + num + " ; count: " + count);
                    return;
                }
                if (!signs[cond]) {
                    signs[cond] = true;
                    q.push(new Inner(cond, count + 1));// 每push 一次 相当于 在 count 基础上加深了一层，
                }
            }
        }
    }


    public static class Inner {
        private Integer num;
        private Integer count;


        public Inner() {
            this.num = 0;
            this.count = 0;
        }

        public Inner(Integer num, Integer count, boolean sign) {
            this.num = num;
            this.count = count;
        }

        public Inner(Integer num, Integer count) {
            this.num = num;
            this.count = count;
        }

        public Integer getNum() {
            return this.num;
        }

        public Integer getCount() {
            return this.count;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
