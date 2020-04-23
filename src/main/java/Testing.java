public class Testing
{
    public static void main(String[] args)
    {
        String h = "$14.00";
        String r = "$14.00";
        String o = h.substring(1);
        String p = r.substring(1);
        double t = Double.parseDouble(o) + Double.parseDouble(p);
        System.out.println(t);
        String[] m = "Sub-Total: $1,049.92".split("\\$");
        System.out.println(m[0]);
        System.out.println(m[1]);
    }
}
