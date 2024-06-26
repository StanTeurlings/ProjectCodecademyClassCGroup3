USE [master]
GO
/****** Object:  Database [CodeCademyGroupC3]    Script Date: 3/29/2024 12:43:00 PM ******/
CREATE DATABASE [CodeCademyGroupC3]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CodeCademyGroupC3', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.STUDENTEN\MSSQL\DATA\CodeCademyGroupC3.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CodeCademyGroupC3_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.STUDENTEN\MSSQL\DATA\CodeCademyGroupC3_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [CodeCademyGroupC3] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CodeCademyGroupC3].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CodeCademyGroupC3] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET ARITHABORT OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CodeCademyGroupC3] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CodeCademyGroupC3] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET  ENABLE_BROKER 
GO
ALTER DATABASE [CodeCademyGroupC3] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CodeCademyGroupC3] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET RECOVERY FULL 
GO
ALTER DATABASE [CodeCademyGroupC3] SET  MULTI_USER 
GO
ALTER DATABASE [CodeCademyGroupC3] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CodeCademyGroupC3] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CodeCademyGroupC3] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CodeCademyGroupC3] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CodeCademyGroupC3] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CodeCademyGroupC3] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'CodeCademyGroupC3', N'ON'
GO
ALTER DATABASE [CodeCademyGroupC3] SET QUERY_STORE = ON
GO
ALTER DATABASE [CodeCademyGroupC3] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [CodeCademyGroupC3]
GO
/****** Object:  Table [dbo].[Certificate]    Script Date: 3/29/2024 12:43:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Certificate](
	[CertificateId] [int] IDENTITY(1,1) NOT NULL,
	[CertificateName] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Certificate_1] PRIMARY KEY CLUSTERED 
(
	[CertificateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Content]    Script Date: 3/29/2024 12:43:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Content](
	[ContentId] [int] IDENTITY(1,1) NOT NULL,
	[PublicationDate] [date] NOT NULL,
	[Status] [varchar](50) NOT NULL,
	[Title] [varchar](255) NOT NULL,
	[Description] [nvarchar](255) NOT NULL,
	[CourseName] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Content] PRIMARY KEY CLUSTERED 
(
	[ContentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 3/29/2024 12:43:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[CourseName] [varchar](100) NOT NULL,
	[CourseSubject] [varchar](255) NOT NULL,
	[IntroductionText] [nvarchar](255) NOT NULL,
	[Difficulty] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Course] PRIMARY KEY CLUSTERED 
(
	[CourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Enrollment]    Script Date: 3/29/2024 12:43:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Enrollment](
	[StudentEmail] [nvarchar](100) NOT NULL,
	[CourseName] [varchar](100) NOT NULL,
	[EnrollmentDate] [date] NOT NULL,
	[CertificateId] [int] NOT NULL,
	[Grade] [float] NULL,
	[EmployeeName] [varchar](255) NULL,
 CONSTRAINT [PK_Enrollment_1] PRIMARY KEY CLUSTERED 
(
	[StudentEmail] ASC,
	[CourseName] ASC,
	[EnrollmentDate] ASC,
	[CertificateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Module]    Script Date: 3/29/2024 12:43:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Module](
	[ContentId] [int] NOT NULL,
	[Version] [nvarchar](50) NOT NULL,
	[ModuleSubject] [varchar](255) NOT NULL,
	[ContactPersonName] [varchar](255) NOT NULL,
	[ContactPersonEmail] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_Module] PRIMARY KEY CLUSTERED 
(
	[ContentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RecommendedCourse]    Script Date: 3/29/2024 12:43:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RecommendedCourse](
	[CourseName] [varchar](100) NOT NULL,
	[RecommendedCourse] [varchar](100) NOT NULL,
 CONSTRAINT [PK_RecommendedCourse] PRIMARY KEY CLUSTERED 
(
	[CourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 3/29/2024 12:43:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[StudentEmail] [nvarchar](100) NOT NULL,
	[StudentName] [varchar](255) NOT NULL,
	[Birthdate] [date] NOT NULL,
	[Gender] [varchar](255) NOT NULL,
	[Address] [varchar](255) NOT NULL,
	[City] [varchar](255) NOT NULL,
	[Country] [varchar](255) NOT NULL,
	[Postcode] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[StudentEmail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ViewedContent]    Script Date: 3/29/2024 12:43:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ViewedContent](
	[StudentEmail] [nvarchar](100) NOT NULL,
	[CourseName] [varchar](255) NOT NULL,
	[PercentageOfViewedContent] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_ViewedContent] PRIMARY KEY CLUSTERED 
(
	[StudentEmail] ASC,
	[CourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Webcast]    Script Date: 3/29/2024 12:43:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Webcast](
	[ContentId] [int] NOT NULL,
	[PresentatorName] [varchar](255) NOT NULL,
	[PresentatorOrganistation] [varchar](255) NOT NULL,
	[WebcastTheme] [varchar](255) NOT NULL,
	[WebcastDuration] [time](7) NOT NULL,
	[URL] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_Webcast] PRIMARY KEY CLUSTERED 
(
	[ContentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Content]  WITH CHECK ADD  CONSTRAINT [FK_Content_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[Content] CHECK CONSTRAINT [FK_Content_Course]
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD  CONSTRAINT [FK_Enrollment_Certificate1] FOREIGN KEY([CertificateId])
REFERENCES [dbo].[Certificate] ([CertificateId])
GO
ALTER TABLE [dbo].[Enrollment] CHECK CONSTRAINT [FK_Enrollment_Certificate1]
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD  CONSTRAINT [FK_Enrollment_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[Enrollment] CHECK CONSTRAINT [FK_Enrollment_Course]
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD  CONSTRAINT [FK_Enrollment_Student] FOREIGN KEY([StudentEmail])
REFERENCES [dbo].[Student] ([StudentEmail])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Enrollment] CHECK CONSTRAINT [FK_Enrollment_Student]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_Content] FOREIGN KEY([ContentId])
REFERENCES [dbo].[Content] ([ContentId])
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_Content]
GO
ALTER TABLE [dbo].[RecommendedCourse]  WITH CHECK ADD  CONSTRAINT [FK_RecommendedCourse_Course1] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[RecommendedCourse] CHECK CONSTRAINT [FK_RecommendedCourse_Course1]
GO
ALTER TABLE [dbo].[ViewedContent]  WITH CHECK ADD  CONSTRAINT [FK_ViewedContent_Student] FOREIGN KEY([StudentEmail])
REFERENCES [dbo].[Student] ([StudentEmail])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ViewedContent] CHECK CONSTRAINT [FK_ViewedContent_Student]
GO
ALTER TABLE [dbo].[Webcast]  WITH CHECK ADD  CONSTRAINT [FK_Webcast_Content] FOREIGN KEY([ContentId])
REFERENCES [dbo].[Content] ([ContentId])
GO
ALTER TABLE [dbo].[Webcast] CHECK CONSTRAINT [FK_Webcast_Content]
GO
USE [master]
GO
ALTER DATABASE [CodeCademyGroupC3] SET  READ_WRITE 
GO
