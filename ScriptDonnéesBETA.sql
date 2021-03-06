USE [master]
GO
/****** Object:  Database [QCM]    Script Date: 18/10/2018 11:53:42 ******/
CREATE DATABASE [QCM]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QCM', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QCM.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QCM_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QCM_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [QCM] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QCM].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QCM] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QCM] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QCM] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QCM] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QCM] SET ARITHABORT OFF 
GO
ALTER DATABASE [QCM] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QCM] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QCM] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QCM] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QCM] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QCM] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QCM] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QCM] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QCM] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QCM] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QCM] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QCM] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QCM] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QCM] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QCM] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QCM] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QCM] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QCM] SET RECOVERY FULL 
GO
ALTER DATABASE [QCM] SET  MULTI_USER 
GO
ALTER DATABASE [QCM] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QCM] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QCM] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QCM] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QCM] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QCM', N'ON'
GO
USE [QCM]
GO
/****** Object:  Table [dbo].[candidat]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[candidat](
	[idUtilisateur] [int] NOT NULL,
	[codePromo] [int] NOT NULL,
 CONSTRAINT [candidat_pk] PRIMARY KEY CLUSTERED 
(
	[idUtilisateur] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[collaborateur]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[collaborateur](
	[idUtilisateur] [int] NOT NULL,
 CONSTRAINT [collaborateur_pk] PRIMARY KEY CLUSTERED 
(
	[idUtilisateur] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[epreuve]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[epreuve](
	[idEpreuve] [int] IDENTITY(1,1) NOT NULL,
	[dateDebutValidite] [datetime] NOT NULL,
	[dateFinValidite] [datetime] NOT NULL,
	[tempsEcoule] [time](7) NULL,
	[etat] [varchar](250) NOT NULL CONSTRAINT [DF__epreuve__etat__0F2D40CE]  DEFAULT ('EA'),
	[noteObtenue] [float] NULL,
	[niveauObtenu] [varchar](250) NULL,
	[utilisateur_idUtilisateur] [int] NOT NULL,
	[test_idTest] [int] NOT NULL,
 CONSTRAINT [epreuve_pk] PRIMARY KEY CLUSTERED 
(
	[idEpreuve] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[profil]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[profil](
	[codeProfil] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](250) NOT NULL,
 CONSTRAINT [profil_pk] PRIMARY KEY CLUSTERED 
(
	[codeProfil] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[promotion]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[promotion](
	[codePromo] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](250) NOT NULL,
 CONSTRAINT [promotion_pk] PRIMARY KEY CLUSTERED 
(
	[codePromo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[proposition]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[proposition](
	[idProposition] [int] IDENTITY(1,1) NOT NULL,
	[enonce] [varchar](250) NOT NULL,
	[estBonne] [bit] NOT NULL,
	[question_idQuestion] [int] NOT NULL,
 CONSTRAINT [proposition_pk] PRIMARY KEY CLUSTERED 
(
	[idProposition] ASC,
	[question_idQuestion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[question]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[question](
	[idQuestion] [int] IDENTITY(1,1) NOT NULL,
	[enonce] [varchar](250) NOT NULL,
	[media] [varchar](250) NULL,
	[points] [int] NOT NULL DEFAULT ((1)),
	[theme_idTheme] [int] NOT NULL,
 CONSTRAINT [question_pk] PRIMARY KEY CLUSTERED 
(
	[idQuestion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[questionTirage]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[questionTirage](
	[estMarquee] [bit] NOT NULL,
	[numOrdre] [int] NOT NULL,
	[epreuve_idEpreuve] [int] NOT NULL,
	[question_idQuestion] [int] NOT NULL,
 CONSTRAINT [PK_questionTirage] PRIMARY KEY CLUSTERED 
(
	[epreuve_idEpreuve] ASC,
	[question_idQuestion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[reponseTirage]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[reponseTirage](
	[questionTirage_epreuve_idEpreuve] [int] NOT NULL,
	[proposition_idProposition] [int] NOT NULL,
	[proposition_question_idQuestion] [int] NOT NULL,
 CONSTRAINT [reponseTirage_pk] PRIMARY KEY CLUSTERED 
(
	[questionTirage_epreuve_idEpreuve] ASC,
	[proposition_idProposition] ASC,
	[proposition_question_idQuestion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[sectionTest]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sectionTest](
	[nbQuestionATirer] [int] NOT NULL,
	[theme_idTheme] [int] NOT NULL,
	[test_idTest] [int] NOT NULL,
 CONSTRAINT [sectionTest_pk] PRIMARY KEY CLUSTERED 
(
	[theme_idTheme] ASC,
	[test_idTest] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[test]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[test](
	[idTest] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](250) NOT NULL,
	[description] [varchar](250) NOT NULL,
	[duree] [time](7) NOT NULL,
	[seuilHaut] [float] NOT NULL,
	[seuilBas] [float] NOT NULL,
 CONSTRAINT [test_pk] PRIMARY KEY CLUSTERED 
(
	[idTest] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[theme]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[theme](
	[idTheme] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](250) NOT NULL,
 CONSTRAINT [theme_pk] PRIMARY KEY CLUSTERED 
(
	[idTheme] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[utilisateur]    Script Date: 18/10/2018 11:53:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[utilisateur](
	[idUtilisateur] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](250) NOT NULL,
	[prenom] [varchar](250) NOT NULL,
	[email] [varchar](250) NOT NULL,
	[password] [varchar](250) NOT NULL,
	[profil_codeProfil] [int] NULL,
 CONSTRAINT [utilisateur_pk] PRIMARY KEY CLUSTERED 
(
	[idUtilisateur] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[candidat] ([idUtilisateur], [codePromo]) VALUES (1, 1)
INSERT [dbo].[candidat] ([idUtilisateur], [codePromo]) VALUES (2, 1)
INSERT [dbo].[candidat] ([idUtilisateur], [codePromo]) VALUES (4, 2)
INSERT [dbo].[candidat] ([idUtilisateur], [codePromo]) VALUES (6, 1)
INSERT [dbo].[collaborateur] ([idUtilisateur]) VALUES (3)
SET IDENTITY_INSERT [dbo].[epreuve] ON 

INSERT [dbo].[epreuve] ([idEpreuve], [dateDebutValidite], [dateFinValidite], [tempsEcoule], [etat], [noteObtenue], [niveauObtenu], [utilisateur_idUtilisateur], [test_idTest]) VALUES (2, CAST(N'2018-01-01 00:00:00.000' AS DateTime), CAST(N'2018-12-12 00:00:00.000' AS DateTime), CAST(N'00:00:10' AS Time), N'T', 0, N'NA', 1, 1)
INSERT [dbo].[epreuve] ([idEpreuve], [dateDebutValidite], [dateFinValidite], [tempsEcoule], [etat], [noteObtenue], [niveauObtenu], [utilisateur_idUtilisateur], [test_idTest]) VALUES (5, CAST(N'2018-01-01 00:00:00.000' AS DateTime), CAST(N'2018-12-12 00:00:00.000' AS DateTime), NULL, N'T', NULL, NULL, 2, 1)
INSERT [dbo].[epreuve] ([idEpreuve], [dateDebutValidite], [dateFinValidite], [tempsEcoule], [etat], [noteObtenue], [niveauObtenu], [utilisateur_idUtilisateur], [test_idTest]) VALUES (9, CAST(N'2018-01-01 00:00:00.000' AS DateTime), CAST(N'2018-12-12 00:00:00.000' AS DateTime), CAST(N'01:20:22' AS Time), N'EA', NULL, NULL, 9, 2)
INSERT [dbo].[epreuve] ([idEpreuve], [dateDebutValidite], [dateFinValidite], [tempsEcoule], [etat], [noteObtenue], [niveauObtenu], [utilisateur_idUtilisateur], [test_idTest]) VALUES (27, CAST(N'2018-01-01 00:00:00.000' AS DateTime), CAST(N'2018-12-12 00:00:00.000' AS DateTime), CAST(N'00:10:00' AS Time), N'EC', 0, N'NA', 2, 3)
SET IDENTITY_INSERT [dbo].[epreuve] OFF
SET IDENTITY_INSERT [dbo].[profil] ON 

INSERT [dbo].[profil] ([codeProfil], [libelle]) VALUES (2, N'CANDIDAT')
INSERT [dbo].[profil] ([codeProfil], [libelle]) VALUES (3, N'COLLABORATEUR')
SET IDENTITY_INSERT [dbo].[profil] OFF
SET IDENTITY_INSERT [dbo].[promotion] ON 

INSERT [dbo].[promotion] ([codePromo], [libelle]) VALUES (1, N'TSIO')
INSERT [dbo].[promotion] ([codePromo], [libelle]) VALUES (2, N'MNIR')
INSERT [dbo].[promotion] ([codePromo], [libelle]) VALUES (3, N'CDA')
SET IDENTITY_INSERT [dbo].[promotion] OFF
SET IDENTITY_INSERT [dbo].[proposition] ON 

INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (1, N'sudo apt-get install maClasse', 0, 1)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (2, N'class maClasse = new maClasse();', 0, 1)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (3, N'class maClasse {}', 1, 1)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (4, N'maClasse classe;', 0, 1)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (5, N'.css', 0, 2)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (6, N'.csharp', 0, 2)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (7, N'.c', 0, 2)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (8, N'.cs', 1, 2)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (9, N'2002', 1, 3)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (10, N'2000', 0, 3)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (11, N'1991', 0, 3)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (12, N'1995', 0, 3)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (13, N'Kirby', 0, 4)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (14, N'Koopa', 0, 4)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (15, N'Fox', 1, 4)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (16, N'Twinsen', 0, 4)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (17, N'Spyro', 0, 6)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (18, N'Medievil', 0, 6)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (19, N'Kirby', 1, 6)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (20, N'Crash Bandicoot', 0, 6)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (21, N'Lara Croft', 0, 7)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (22, N'Nytro', 0, 7)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (23, N'Twinsen', 1, 7)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (24, N'Luigi', 0, 7)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (25, N'Wario', 0, 8)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (26, N'Toadette', 1, 8)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (27, N'Toad', 1, 8)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (28, N'Silfyl', 0, 8)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (29, N'Pikachu', 0, 13)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (31, N'Falco', 0, 13)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (32, N'Link', 1, 13)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (33, N'Boo', 0, 13)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (34, N'Kirby', 0, 14)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (35, N'Spyro', 1, 14)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (36, N'Aladdin', 0, 14)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (37, N'Yoshi', 0, 14)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (38, N'Kirby', 0, 15)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (40, N'Spyro', 0, 15)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (42, N'Pandemonium', 0, 15)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (43, N'Tombi', 1, 15)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (44, N'France Gall', 0, 16)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (45, N'Edith Piaf', 1, 16)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (46, N'Céline Dion', 0, 16)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (47, N'Je t''aime', 0, 17)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (48, N'Je l''aime à mourir', 1, 17)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (49, N'Caravane', 0, 17)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (50, N'Part of Me', 0, 18)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (51, N' Y.M.C.A.', 0, 18)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (52, N'Last Friday Night', 1, 18)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (53, N'Rita Ora', 0, 19)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (54, N'Martin Garrix', 0, 19)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (55, N'Bebe Rexha', 1, 19)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (56, N'Soprano', 1, 20)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (57, N'Maître Gims', 0, 20)
INSERT [dbo].[proposition] ([idProposition], [enonce], [estBonne], [question_idQuestion]) VALUES (59, N'Kendji Girac', 1, 20)
SET IDENTITY_INSERT [dbo].[proposition] OFF
SET IDENTITY_INSERT [dbo].[question] ON 

INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (1, N'Comment créer une classe ?', N'unePicture', 10, 1)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (2, N'Quel est la bonne extension de fichier ?', N'monImage.png', 10, 2)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (3, N'Quelle est son année de création ?', N'photoCreateur.jpeg', 10, 1)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (4, N'Petite boule de poils roux, je suis rusé comme un renard. Je suis...', N'renard.jpg', 2, 3)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (6, N'Petit boule rose je m''envole en me remplissant d''air et si je gobe un ennemi, je peux lui voler son pouvoir si j''appuie sur la touche du bas. Je suis...', N'dragibus.jpg', 2, 3)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (7, N'Je suis le héros du jeu ''Little Big Adventure'', je me sauve d''une ville assiégée par l''armée et je fais le tour du monde. Je suis...', N'lba.jpg', 2, 3)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (8, N'Pour quelques-uns, je suis un petit personnage sympa de l''univers de Mario, pour d''autres, je serais un super bon ingrédient dans une omelette aux champignons. Je suis... ?', N'amanite.jpg', 2, 3)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (13, N'Je suis obligé de parcourir le royaume D''Hyrule afin de sauver ma princesse et de trouver les différents morceaux de la Triforce. Je suis...', N'triforce.jpg', 2, 3)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (14, N'Je vole à travers des royaumes colorés et je collecte des diamants en crachant du feu ? Alors, vous me reconnaissez ?', N'feu.jpg', 2, 3)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (15, N'Je suis une petite créature qui ne fait qu''errer dans le décor du jeu. C''est à vous de me faire escalader, creuser, poser une bombe... Alors, qui suis-je ?', N'play.jpg', 2, 3)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (16, N'Qui est l''auteur(e) de "La foule" ?', N'foule.jpg', 2, 4)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (17, N'Quelle chanson française Shakira a-t-elle reprise ?', N'shakira.jpg', 2, 4)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (18, N'Dans quelle chanson peut-on entendre "T.G.I.F." ?', N'tgif.jpg', 2, 4)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (19, N'Qui chante "In the Name of Love" ?', N'love.png', 2, 4)
INSERT [dbo].[question] ([idQuestion], [enonce], [media], [points], [theme_idTheme]) VALUES (20, N'Qui chante "No me mirès màs" ?', N'guitar.jpg', 2, 4)
SET IDENTITY_INSERT [dbo].[question] OFF
INSERT [dbo].[questionTirage] ([estMarquee], [numOrdre], [epreuve_idEpreuve], [question_idQuestion]) VALUES (0, 5, 27, 4)
INSERT [dbo].[questionTirage] ([estMarquee], [numOrdre], [epreuve_idEpreuve], [question_idQuestion]) VALUES (0, 2, 27, 7)
INSERT [dbo].[questionTirage] ([estMarquee], [numOrdre], [epreuve_idEpreuve], [question_idQuestion]) VALUES (0, 1, 27, 8)
INSERT [dbo].[questionTirage] ([estMarquee], [numOrdre], [epreuve_idEpreuve], [question_idQuestion]) VALUES (0, 4, 27, 14)
INSERT [dbo].[questionTirage] ([estMarquee], [numOrdre], [epreuve_idEpreuve], [question_idQuestion]) VALUES (0, 3, 27, 15)
INSERT [dbo].[questionTirage] ([estMarquee], [numOrdre], [epreuve_idEpreuve], [question_idQuestion]) VALUES (0, 9, 27, 16)
INSERT [dbo].[questionTirage] ([estMarquee], [numOrdre], [epreuve_idEpreuve], [question_idQuestion]) VALUES (0, 10, 27, 17)
INSERT [dbo].[questionTirage] ([estMarquee], [numOrdre], [epreuve_idEpreuve], [question_idQuestion]) VALUES (0, 7, 27, 18)
INSERT [dbo].[questionTirage] ([estMarquee], [numOrdre], [epreuve_idEpreuve], [question_idQuestion]) VALUES (0, 8, 27, 19)
INSERT [dbo].[questionTirage] ([estMarquee], [numOrdre], [epreuve_idEpreuve], [question_idQuestion]) VALUES (0, 6, 27, 20)
INSERT [dbo].[sectionTest] ([nbQuestionATirer], [theme_idTheme], [test_idTest]) VALUES (1, 1, 1)
INSERT [dbo].[sectionTest] ([nbQuestionATirer], [theme_idTheme], [test_idTest]) VALUES (1, 2, 1)
INSERT [dbo].[sectionTest] ([nbQuestionATirer], [theme_idTheme], [test_idTest]) VALUES (5, 3, 3)
INSERT [dbo].[sectionTest] ([nbQuestionATirer], [theme_idTheme], [test_idTest]) VALUES (5, 4, 3)
SET IDENTITY_INSERT [dbo].[test] ON 

INSERT [dbo].[test] ([idTest], [libelle], [description], [duree], [seuilHaut], [seuilBas]) VALUES (1, N'Test de C#', N'Apprendre le C# pour les étudiants facilement', CAST(N'00:00:10' AS Time), 15, 10)
INSERT [dbo].[test] ([idTest], [libelle], [description], [duree], [seuilHaut], [seuilBas]) VALUES (2, N'Test de C++', N'On y voit tout ce qui concerne le C++', CAST(N'00:30:00' AS Time), 15, 10)
INSERT [dbo].[test] ([idTest], [libelle], [description], [duree], [seuilHaut], [seuilBas]) VALUES (3, N'Jeux Vidéo et Musique', N'Test sur les héros de jeux vidéo et la Musique', CAST(N'00:10:00' AS Time), 12, 10)
SET IDENTITY_INSERT [dbo].[test] OFF
SET IDENTITY_INSERT [dbo].[theme] ON 

INSERT [dbo].[theme] ([idTheme], [libelle]) VALUES (1, N'C++')
INSERT [dbo].[theme] ([idTheme], [libelle]) VALUES (2, N'C#')
INSERT [dbo].[theme] ([idTheme], [libelle]) VALUES (3, N'Jeux Vidéo')
INSERT [dbo].[theme] ([idTheme], [libelle]) VALUES (4, N'Musique')
SET IDENTITY_INSERT [dbo].[theme] OFF
SET IDENTITY_INSERT [dbo].[utilisateur] ON 

INSERT [dbo].[utilisateur] ([idUtilisateur], [nom], [prenom], [email], [password], [profil_codeProfil]) VALUES (1, N'Oulai', N'Isaac', N'isaac-oulai@campus.fr', N'passwordIsaac', 2)
INSERT [dbo].[utilisateur] ([idUtilisateur], [nom], [prenom], [email], [password], [profil_codeProfil]) VALUES (2, N'Pineau', N'Clément', N'c.pineau@campus.fr', N'password', 3)
INSERT [dbo].[utilisateur] ([idUtilisateur], [nom], [prenom], [email], [password], [profil_codeProfil]) VALUES (3, N'Chiron', N'Audric', N'audric-chiron@campus.fr', N'passwordAudric', 3)
INSERT [dbo].[utilisateur] ([idUtilisateur], [nom], [prenom], [email], [password], [profil_codeProfil]) VALUES (4, N'Tapie', N'Bernard', N'b.tapie@campus.fr', N'nanard', 2)
INSERT [dbo].[utilisateur] ([idUtilisateur], [nom], [prenom], [email], [password], [profil_codeProfil]) VALUES (6, N'Plastique', N'Bertrand', N'b.plastique@campus-eni.fr', N'beber', 2)
SET IDENTITY_INSERT [dbo].[utilisateur] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [utilisateur_email_un]    Script Date: 18/10/2018 11:53:42 ******/
ALTER TABLE [dbo].[utilisateur] ADD  CONSTRAINT [utilisateur_email_un] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[candidat]  WITH CHECK ADD  CONSTRAINT [candidat_promotion_fk] FOREIGN KEY([codePromo])
REFERENCES [dbo].[promotion] ([codePromo])
GO
ALTER TABLE [dbo].[candidat] CHECK CONSTRAINT [candidat_promotion_fk]
GO
ALTER TABLE [dbo].[candidat]  WITH CHECK ADD  CONSTRAINT [candidat_utilisateur_fk] FOREIGN KEY([idUtilisateur])
REFERENCES [dbo].[utilisateur] ([idUtilisateur])
GO
ALTER TABLE [dbo].[candidat] CHECK CONSTRAINT [candidat_utilisateur_fk]
GO
ALTER TABLE [dbo].[collaborateur]  WITH CHECK ADD  CONSTRAINT [collaborateur_utilisateur_fk] FOREIGN KEY([idUtilisateur])
REFERENCES [dbo].[utilisateur] ([idUtilisateur])
GO
ALTER TABLE [dbo].[collaborateur] CHECK CONSTRAINT [collaborateur_utilisateur_fk]
GO
ALTER TABLE [dbo].[epreuve]  WITH CHECK ADD  CONSTRAINT [epreuve_test_fk] FOREIGN KEY([test_idTest])
REFERENCES [dbo].[test] ([idTest])
GO
ALTER TABLE [dbo].[epreuve] CHECK CONSTRAINT [epreuve_test_fk]
GO
ALTER TABLE [dbo].[epreuve]  WITH CHECK ADD  CONSTRAINT [epreuve_utilisateur_fk] FOREIGN KEY([utilisateur_idUtilisateur])
REFERENCES [dbo].[utilisateur] ([idUtilisateur])
GO
ALTER TABLE [dbo].[epreuve] CHECK CONSTRAINT [epreuve_utilisateur_fk]
GO
ALTER TABLE [dbo].[proposition]  WITH CHECK ADD  CONSTRAINT [proposition_question_fk] FOREIGN KEY([question_idQuestion])
REFERENCES [dbo].[question] ([idQuestion])
GO
ALTER TABLE [dbo].[proposition] CHECK CONSTRAINT [proposition_question_fk]
GO
ALTER TABLE [dbo].[question]  WITH CHECK ADD  CONSTRAINT [question_theme_fk] FOREIGN KEY([theme_idTheme])
REFERENCES [dbo].[theme] ([idTheme])
GO
ALTER TABLE [dbo].[question] CHECK CONSTRAINT [question_theme_fk]
GO
ALTER TABLE [dbo].[questionTirage]  WITH CHECK ADD  CONSTRAINT [questionTirage_epreuve_fk] FOREIGN KEY([epreuve_idEpreuve])
REFERENCES [dbo].[epreuve] ([idEpreuve])
GO
ALTER TABLE [dbo].[questionTirage] CHECK CONSTRAINT [questionTirage_epreuve_fk]
GO
ALTER TABLE [dbo].[questionTirage]  WITH CHECK ADD  CONSTRAINT [questionTirage_question_fk] FOREIGN KEY([question_idQuestion])
REFERENCES [dbo].[question] ([idQuestion])
GO
ALTER TABLE [dbo].[questionTirage] CHECK CONSTRAINT [questionTirage_question_fk]
GO
ALTER TABLE [dbo].[sectionTest]  WITH CHECK ADD  CONSTRAINT [sectionTest_test_fk] FOREIGN KEY([test_idTest])
REFERENCES [dbo].[test] ([idTest])
GO
ALTER TABLE [dbo].[sectionTest] CHECK CONSTRAINT [sectionTest_test_fk]
GO
ALTER TABLE [dbo].[sectionTest]  WITH CHECK ADD  CONSTRAINT [sectionTest_theme_fk] FOREIGN KEY([theme_idTheme])
REFERENCES [dbo].[theme] ([idTheme])
GO
ALTER TABLE [dbo].[sectionTest] CHECK CONSTRAINT [sectionTest_theme_fk]
GO
ALTER TABLE [dbo].[utilisateur]  WITH CHECK ADD  CONSTRAINT [utilisateur_profil_fk] FOREIGN KEY([profil_codeProfil])
REFERENCES [dbo].[profil] ([codeProfil])
GO
ALTER TABLE [dbo].[utilisateur] CHECK CONSTRAINT [utilisateur_profil_fk]
GO
ALTER TABLE [dbo].[epreuve]  WITH CHECK ADD  CONSTRAINT [CK__epreuve__etat__10216507] CHECK  (([etat]='T' OR [etat]='EC' OR [etat]='EA'))
GO
ALTER TABLE [dbo].[epreuve] CHECK CONSTRAINT [CK__epreuve__etat__10216507]
GO
ALTER TABLE [dbo].[epreuve]  WITH CHECK ADD  CONSTRAINT [CK__epreuve__niveauO__11158940] CHECK  (([niveauObtenu]='NA' OR [niveauObtenu]='ECA' OR [niveauObtenu]='A'))
GO
ALTER TABLE [dbo].[epreuve] CHECK CONSTRAINT [CK__epreuve__niveauO__11158940]
GO
USE [master]
GO
ALTER DATABASE [QCM] SET  READ_WRITE 
GO
