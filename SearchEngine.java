import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {

    public String handleRequest(URI url) {
        ArrayList<String> addedString = new ArrayList<String>();
        ArrayList<String> foundString = new ArrayList<String>();
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    addedString.add(parameters[1]);
                    return String.format("String added!");
                } 
                else {
                    return "Nothing to add";
                }

            } else if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    for(String s : addedString){
                        if (s.contains(parameters[1])){
                            foundString.add(s);
                        }
                    }
                }
                if(foundString.size() != 0) {
                    return String.format(foundString.toString());
                }
                else{
                    return "none";
                }
            }
            else{
                return "none";
            }
        }
    }


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
