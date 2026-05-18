import 'package:flutter/material.dart';
import 'package:movie_app/features/peliculas/presentation/pages/inicio_page.dart';
import 'package:movie_app/features/peliculas/providers/movie_provider.dart';
import 'package:provider/provider.dart';
import 'package:intl/intl.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();

  // cargar variables del .env
  await dotenv.load(fileName: "api.env");

  Intl.defaultLocale = 'es_ES';

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => MovieProvider()),
      ],
      child: MaterialApp(
        title: 'Movie App',
        debugShowCheckedModeBanner: false,
        home: InicioPage(),
      ),
    );
  }
}