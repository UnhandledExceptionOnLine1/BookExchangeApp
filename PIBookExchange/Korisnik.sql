USE BookExchangePlatformDB
GO
--------------------------------
CREATE TABLE Korisnik
(
	IDKorisnik INT PRIMARY KEY IDENTITY,
	KorisnickoIme NVARCHAR(50),
	Lozinka NVARCHAR(50),
	Ime NVARCHAR(50),
	Prezime NVARCHAR(50),
	Adresa NVARCHAR(50),
	Telefon NVARCHAR(20),
	Email NVARCHAR(50)
)
GO
--------------------------------
CREATE or ALTER PROCEDURE CreateUser
	@KorisnickoIme NVARCHAR(50),
	@Lozinka NVARCHAR(50),
	@Ime NVARCHAR(50),
	@Prezime NVARCHAR(50),
	@Adresa NVARCHAR(50),
	@Telefon NVARCHAR(50),
	@Email NVARCHAR(50),
	@id INT OUTPUT
AS
BEGIN
	INSERT INTO Korisnik (KorisnickoIme, Lozinka, Ime, Prezime, Adresa, Telefon, Email)
	VALUES (@KorisnickoIme, @Lozinka, @Ime, @Prezime, @Adresa, @Telefon, @Email)
	SET @id = SCOPE_IDENTITY()
END
GO
--------------------------------
CREATE or ALTER PROCEDURE GetUser
	@IDKorisnik INT
AS
BEGIN
	IF NOT EXISTS (SELECT 1 FROM Korisnik WHERE IDKorisnik = @IDKorisnik)
		BEGIN
		RAISERROR ('Korisnik sa zadanim ID-jem ne postoji', 16, 1)
		RETURN
		END
	SELECT IDKorisnik, KorisnickoIme, Ime, Prezime, Adresa, Telefon, Email
	FROM Korisnik
	WHERE IDKorisnik = @IDKorisnik
END
GO
--------------------------------
CREATE or ALTER PROCEDURE GetAllUsers
AS
BEGIN
	SELECT IDKorisnik, KorisnickoIme, Ime, Prezime, Adresa, Telefon, Email
	FROM Korisnik
END
GO
--------------------------------
CREATE or ALTER PROCEDURE UpdateUser
	@IDKorisnik INT,
	@KorisnickoIme NVARCHAR(50),
	@Lozinka NVARCHAR(50),
	@Ime NVARCHAR(50),
	@Prezime NVARCHAR(50),
	@Adresa NVARCHAR(50),
	@Telefon NVARCHAR(50),
	@Email NVARCHAR(50),
	@Message NVARCHAR(250) OUTPUT
AS
BEGIN
	UPDATE Korisnik
	SET 
		KorisnickoIme = @KorisnickoIme,
        Lozinka = @Lozinka,
        Ime = @Ime,
        Prezime = @Prezime,
        Adresa = @Adresa,
        Telefon = @Telefon,
        Email = @Email
    WHERE IDKorisnik = @IDKorisnik;
    IF @@ROWCOUNT = 0
    BEGIN
        SET @Message = 'Korisnik s ID-jem ne postoji.';
    END
    ELSE
    BEGIN
        SET @Message = 'Ažuriranje korisnika uspješno.';
    END
END
GO
--------------------------------
CREATE or ALTER PROCEDURE DeleteUser
	@IDKorisnik INT,
	@Message NVARCHAR(250) OUTPUT
AS
BEGIN
	IF NOT EXISTS (SELECT 1 FROM Korisnik WHERE IDKorisnik = @IDKorisnik)
		BEGIN
		RAISERROR ('Korisnik sa zadanim ID-jem ne postoji', 16, 1)
		RETURN
		END
	DELETE FROM Korisnik
	WHERE IDKorisnik = @IDKorisnik
		IF @@ROWCOUNT = 0
    BEGIN
        SET @Message = 'Korisnik s ID-jem ne postoji.';
    END
    ELSE
    BEGIN
        SET @Message = 'Brisanje korisnika uspješno.';
    END
END
GO
--------------------------------
