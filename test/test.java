public class test {

    public static void main(String[] args) {
        String[] arr = {"yi","二","3s"};
        Stream<String> stream = Arrays.stream(arr);
        System.out.println(stream);

        List<String> list = Arrays.asList(arr);
        Stream<String> streamList = list.stream();
        System.out.println(streamList);

    }

}