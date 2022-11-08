import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @ClassName Solution
 * @Author 23DAY
 * @Date 2022/11/8 14:11
 * @Version 1.0
 */
public class Solution {

    /**
     * @return java.math.BigInteger
     * @Description 辗转相除
     * @Author 23DAY
     * @Date 2022/11/8 14:21
     * @Param [java.math.BigInteger, java.math.BigInteger]
     **/
    static BigInteger gcd(BigInteger a, BigInteger b) {

        if (b.intValue() == 0) {
            return a;
        }
        return gcd(b, b.mod(a));
    }

    /**
     * @return java.lang.Boolean
     * @Description 判断是否为奇素数
     * @Author 23DAY
     * @Date 2022/11/8 14:39
     * @Param [java.math.BigInteger]
     **/
    static Boolean judgeValid(BigInteger x) {
        boolean flag = true;
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(x) < 0; i = i.add(BigInteger.ONE)) {
            if (x.mod(i).equals(BigInteger.valueOf(0))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * @return java.math.BigInteger
     * @Description b^n%m
     * @Author 23DAY
     * @Date 2022/11/8 14:52
     * @Param [java.math.BigInteger, java.math.BigInteger, java.math.BigInteger]
     **/
    static BigInteger getPower(BigInteger b, BigInteger n, BigInteger m) {

        //递归出口
        if (n.equals(BigInteger.ONE)) {
            return b;
        }
        BigInteger tmp = getPower(b, n.divide(BigInteger.valueOf(2)), m);
        if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
            return tmp.multiply(tmp).mod(m).multiply(b).mod(m);
        } else {
            return tmp.multiply(tmp).mod(m);
        }
    }

    /**
     * @return java.util.List<java.math.BigInteger>
     * @Description 求因数
     * @Author 23DAY
     * @Date 2022/11/8 14:53
     * @Param [java.math.BigInteger]
     **/
    static List<BigInteger> getFactor(BigInteger x) {
        ArrayList<BigInteger> factors = new ArrayList<>();
        for (BigInteger i = BigInteger.ONE; i.compareTo(x) <= 0; i = i.add(BigInteger.ONE)) {
            if (x.mod(i).equals(BigInteger.valueOf(0))) {
                factors.add(i);
            }
        }
        return factors;
    }

    /**
     * @return java.util.List<java.math.BigInteger>
     * @Description 求素因数
     * @Author 23DAY
     * @Date 2022/11/8 14:59
     * @Param [java.math.BigInteger]
     **/
    static List<BigInteger> getPrimeFactor(BigInteger x) {
        ArrayList<BigInteger> factors = new ArrayList<>();
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(x) <= 0; i = i.add(BigInteger.ONE)) {
            if (x.mod(i).equals(BigInteger.valueOf(0))) {
                factors.add(i);
                while (x.mod(i).equals(BigInteger.valueOf(0))) {
                    x = x.divide(i);
                }
            }
        }
        return factors;
    }

    /**
     * @return java.math.BigInteger
     * @Description 求指数
     * @Author 23DAY
     * @Date 2022/11/8 15:01
     * @Param [java.util.List<java.math.BigInteger>, java.math.BigInteger, java.math.BigInteger]
     **/
    static BigInteger getExponent(List<BigInteger> factors, BigInteger a, BigInteger m) {
        for (BigInteger factor : factors) {
//            if (getPower(a, factor, m).equals(BigInteger.ONE)) {
//                return factor;
//            }
            if (a.modPow(factor,m).equals(BigInteger.ONE)) {
                return factor;
            }
        }
        return a;
    }

    /**
     * @return java.math.BigInteger
     * @Description 求一个原根
     * @Author 23DAY
     * @Date 2022/11/8 15:08
     * @Param [java.util.List<java.math.BigInteger>, java.math.BigInteger, java.math.BigInteger]
     **/
    static BigInteger getPrimordialRoot(List<BigInteger> factors, BigInteger m, BigInteger eulerValue) {
        factors.replaceAll(eulerValue::divide);
        BigInteger a = BigInteger.valueOf(2);
        boolean flag;
        while (true) {
            flag = false;
            for (BigInteger factor : factors) {
//                if (getPower(a, factor, m).equals(BigInteger.ONE)) {
//                    flag = true;
//                    break;
//                }
                if (a.modPow(factor,m).equals(BigInteger.ONE)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return a;
            }
            a = a.add(BigInteger.ONE);
        }
    }

    /**
     * @return java.util.List<java.math.BigInteger>
     * @Description 简化剩余系
     * @Author 23DAY
     * @Date 2022/11/8 15:11
     * @Param [java.math.BigInteger]
     **/
    static List<BigInteger> getRemains(BigInteger eulerValue) {
        ArrayList<BigInteger> remains = new ArrayList<>();
        for (BigInteger i = BigInteger.ONE; i.compareTo(eulerValue) < 0; i = i.add(BigInteger.ONE)) {
//            if (i.gcd(eulerValue).equals(BigInteger.ONE)) {
//                remains.add(i);
//            }
            if (gcd(i,eulerValue).equals(BigInteger.ONE)) {
                remains.add(i);
            }
        }
        return remains;
    }

    /**
     * @return java.util.List<java.math.BigInteger>
     * @Description 求原根
     * @Author 23DAY
     * @Date 2022/11/8 15:16
     * @Param [java.util.List<java.math.BigInteger>, java.math.BigInteger, java.math.BigInteger]
     **/
    static List<BigInteger> getPrimordialRoots(List<BigInteger> remains, BigInteger m, BigInteger primordialRoot) {
        List<BigInteger> primordialRoots = new ArrayList<>();
        for (BigInteger remain : remains) {
            //primordialRoots.add(getPower(primordialRoot, remain, m));
            primordialRoots.add(primordialRoot.modPow(remain,m));
        }
        return primordialRoots;
    }

    public static void main(String[] args) {
//        Scanner reader = new Scanner(System.in);
//        BigInteger a = reader.nextBigInteger();
//        BigInteger p = reader.nextBigInteger();

        long beginTime = System.currentTimeMillis();
        System.out.println("-------------------------------------------------");

//	    输入a,p
        BigInteger a = BigInteger.valueOf(2);
        BigInteger p = new BigInteger("1200013111");
//        BigInteger p = BigInteger.valueOf(220001312);
//  	判断是否为奇素数
//        if (!judgeValid(p) || !(p % 2)) {
//            cout << "不合法";
//            return 0;
//        }
//	    求φ(p)=p-1
        BigInteger eulerValue = p.subtract(BigInteger.ONE);
        System.out.println("欧拉：" + eulerValue);


        long endTime = System.currentTimeMillis();
        System.out.println("时间：" + (endTime - beginTime) + "ms");
        System.out.println("-------------------------------------------------");

//	    求p-1的因数
        List<BigInteger> factors = getFactor(eulerValue);
//        System.out.println("因数：" + factors);

//	    再用a^{p-1}=1计算最小的：指数
        BigInteger exponent = getExponent(factors, a, p);
        System.out.println("指数：" + exponent);

//	    求p-1的素因数
        List<BigInteger> prime_factors = getPrimeFactor(eulerValue);
//        System.out.println("素因数：" + prime_factors);

//	    求一个原根
        BigInteger singlePrimordialRoot = getPrimordialRoot(prime_factors, p, eulerValue);
        System.out.println("一个原根：" + singlePrimordialRoot);
        endTime = System.currentTimeMillis();
        System.out.println("时间：" + (endTime - beginTime) + "ms");
        System.out.println("-------------------------------------------------");

//	    求p-1的简化剩余系
        List<BigInteger> remains = getRemains(eulerValue);
//        System.out.println("简化剩余系：" + remains);
        System.out.println("简化剩余系：");
        endTime = System.currentTimeMillis();
        System.out.println("时间：" + (endTime - beginTime) + "ms");
        System.out.println("-------------------------------------------------");

//	    利用简化剩余系求原根
        List<BigInteger> primordialRoots = getPrimordialRoots(remains, p, singlePrimordialRoot);
//        System.out.println("原根：" + primordialRoots);
        System.out.println("原根：");

        endTime = System.currentTimeMillis();
        System.out.println("时间：" + (endTime - beginTime) + "ms");
        System.out.println("-------------------------------------------------");

    }
}
