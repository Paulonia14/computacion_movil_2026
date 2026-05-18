import 'package:curved_navigation_bar/curved_navigation_bar.dart';
import 'package:flutter/material.dart';
import 'package:movie_app/core/utils/app_colors.dart';
import 'package:movie_app/features/peliculas/presentation/pages/favoritos_page.dart';
import 'package:movie_app/features/peliculas/presentation/pages/home_page.dart';
import 'package:movie_app/features/peliculas/presentation/pages/perfil_page.dart';

class InicioPage extends StatefulWidget {
  const InicioPage({super.key});

  @override
  State<InicioPage> createState() => _InicioPageState();
}

class _InicioPageState extends State<InicioPage> {

  int _currentIndex = 0;
  List<Widget> pages = [  
    HomePage(),
    FavoritosPage(),
    PerfilPage(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      extendBody: true,
      body: pages[_currentIndex],
      bottomNavigationBar: CurvedNavigationBar(
        backgroundColor: Colors.transparent,
        buttonBackgroundColor: AppColors.accent,
        color: AppColors.primary,
        index: _currentIndex,
        animationDuration: const Duration(milliseconds: 300),
        items: const [
        Icon(Icons.home, size:30, color: Colors.white),
        Icon(Icons.favorite, size:30, color: Colors.white),
        Icon(Icons.person, size:30, color: Colors.white),
      ],
      onTap: (index) {
        setState(() {
          _currentIndex = index;
        });
      },
      ),
    );
  }
}