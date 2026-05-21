import 'package:flutter/material.dart';
import 'package:movie_app/features/peliculas/providers/movie_provider.dart';
import 'package:provider/provider.dart';
import 'package:card_swiper/card_swiper.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  PageController pageController = PageController();
  int currentIndex = 0;

  @override
  Widget build(BuildContext context) {
    final moviesProvider = Provider.of<MovieProvider>(context);
    //final moviesProvider = context.read<MovieProvider>();
    // final imagesWithTop = moviesProvider.
    return Scaffold(
      extendBody: true,
      body: Stack(
        children: [
          PageView.builder(
            controller: pageController,
            itemCount: moviesProvider.onDisplayMovies.length,
            physics: const NeverScrollableScrollPhysics(),
            itemBuilder: (BuildContext context, int index) {
              final movie = moviesProvider.onDisplayMovies[index];
              return Container(
                decoration: BoxDecoration(
                  image: DecorationImage(
                    image: NetworkImage(
                      'https://image.tmdb.org/t/p/w500${movie.posterPath}',
                    ), 
                    fit: BoxFit.cover,
                  ),
                ), 
                child: Container(
                  decoration: BoxDecoration(
                    gradient: LinearGradient(
                      begin: Alignment.topCenter,
                      end: Alignment.bottomCenter,
                      colors: [
                        Colors.black26,
                        Colors.black,
                      ],
                      stops: const [0.0, 0.5, 1.0],
                    ), 
                  ), 
                ),
              );
            },
          ), 
          Swiper(
            itemCount: moviesProvider.onDisplayMovies.length,
            itemBuilder: (BuildContext context, int index) {
              final movie = moviesProvider.onDisplayMovies[index];
              return Padding(
                padding: const EdgeInsets.all(50.0),
                child: ClipRRect(
                  borderRadius: BorderRadius.circular(20),
                  child: Image.network(
                    'https://image.tmdb.org/t/p/w500${movie.posterPath}',
                    fit: BoxFit.cover,
                    height: 300,
                    width: 200,
                  ), 
                ), 
              ); 
            },
            onIndexChanged: (index) {
              setState(() {
                currentIndex = index;
              });
              pageController.animateToPage(
                index,
                duration: const Duration(milliseconds: 300),
                curve: Curves.easeInOut,
              );
            },
          ),
        ],
      ),
    ); 
  }
}