package lt.chomicenko.final_api.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.chomicenko.final_api.model.Poster;
import lt.chomicenko.final_api.service.PosterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class PosterDatabaseInitializer implements CommandLineRunner {

    private final PosterService posterService;

    @Override
    public void run(String... args) {
        if (!posterService.getPosters().isEmpty()) {
            return;
        }
        POSTERS.forEach(poster -> {
            posterService.savePoster(poster);
        });
        log.info("Database initialized with posters");
    }

    private static final List<Poster> POSTERS = Arrays.asList(
            new Poster(
                    "1564156165",
                    "Ferrari",
                    "Way to heaven",
                    "Vilnius",
                    "https://s1.15min.lt/static/cache/MTkyMHgxMDgwLDQxNHg0MjQsNjAwNDM3LG9yaWdpbmFsLCxpZD01NTAyODA2JmRhdGU9MjAyMCUyRjA4JTJGMTksNTQ3MzQzNzgx/ferrari-f8-tributo-5f3d402aef4f7.jpg",
                    "416,000",
                    "Cars"
            ),
            new Poster(
                    "2554654",
                    "Lamborghini",
                    "Back from the heaven",
                    "Kuršėnai",
                    "https://hips.hearstapps.com/hmg-prod/images/2023-lamborghini-huracan-sterrato127-6467c8f12dcce.jpg?crop=0.595xw:0.445xh;0.174xw,0.447xh&resize=1200:*",
                    "180,000",
                    "Cars"
            ),
            new Poster(
                    "356564655",
                    "BMX bike",
                    "Brand new bike - Your Journey to...",
                    "Kretinga",
                    "https://external-preview.redd.it/TNMrQiLAl1YOD_Z4oDWn3t3R9qZCfn2l3CcjRvo9Bks.jpg?auto=webp&s=682839cfacd63a5335b30b579352cc968cb42cef",
                    "950",
                    "Bikes"
            ),
            new Poster(
                    "4556416165",
                    "Bike-Cock",
                    "Get all hens",
                    "Ignalina",
                    "https://gratisography.com/wp-content/uploads/2018/05/gratisography-410H-free-stock-photo-894x600.jpg",
                    "1500",
                    "Bikes"
            ),

            new Poster(
                    "5546564",
                    "Fast and Furious",
                    "Discover everything you like",
                    "Kaunas",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCj3h_wYU5L7aee8laHjIdDiOpT0u3g_q16A&s",
                    "Bargain",
                    "Services"
            ),

            new Poster(
                    "66564456565",
                    "Forget about issues",
                    "Quick fix",
                    "Klaipėda",
                    "https://i.pinimg.com/originals/60/26/15/602615deaee5ef0edc93438af522f54f.png",
                    "Not much",
                    "Services"
            ),

            new Poster(
                    "956598",
                    "Homer",
                    "You will love it",
                    "Palanga",
                    "https://media.timeout.com/images/105757912/image.jpg",
                    "145",
                    "Inflatables"
            ),

            new Poster(
                    "9514515262",
                    "Empire of Horror",
                    "You will not regret",
                    "Palanga",
                    "https://www.quickdrawsupplies.com/wp-content/uploads/2020/07/c00-735-2.jpg",
                    "150",
                    "Inflatables"
            )



    );
}
