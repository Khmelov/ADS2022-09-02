program Project11;

uses
  Vcl.Forms,
  Unit3 in 'Unit3.pas' {ExportSystem};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TExportSystem, ExportSystem);
  Application.Run;
end.
