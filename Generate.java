import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Generate {

    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("original.csv")).forEach(e -> {
            var data = e.trim().split(",");
            String text = """
                    // $NAME$
                    switch(FEAT_TRAINS, SELF, train$ID$_speed, current_railtype) {
                    $RAIL$: $SPEED$ - $rail$_penalty;
                    UNI1: max(10, $SPEED$ - uni1_penalty);
                    UNI2: max(10, $SPEED$ - uni2_penalty);
                    UNI3: max(10, $SPEED$ - uni3_penalty);
                    UNI4: max(10, $SPEED$ - uni4_penalty);
                    UNI5: max(10, $SPEED$ - uni5_penalty);
                    $SPEED$ - $rail$_penalty;
                    }
                                            
                    item(FEAT_TRAINS, train$ID$, $ID$) {
                        property {
                            climates_available: ALL_CLIMATES;
                        }
                        graphics {
                            speed: train$ID$_speed;
                        }
                    }
                    """;
            System.out.println(text
                    .replace("$ID$", data[0])
                    .replace("$NAME$", data[1])
                    .replace("$SPEED$", data[2])
                    .replace("$RAIL$", data[3])
                    .replace("$rail$", data[3].toLowerCase()));
        });
    }
}
