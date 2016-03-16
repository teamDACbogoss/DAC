DBeaver

3.6.1

README

Thank you for downloading DBeaver!

Installing DBeaver
========================
  Use automatic installer distribution (Windows and Linux versions)
  or just unpack archive and start "dbeaver" executable.
  
  Note: do not extract archive in the same folder where older version
  of DBeaver is installed.
  Remove previous version or extract archive in another folder.

Command line parameters
========================

  -data <path>
    Store all projects/configuration in folder <path>. By default DBeaver
	stores all its data in user's home ".dbeaver" folder.
	<path> could be an absolute or relative directory name.
	If you want to use DBeaver as redistributable program start it
	with arguments like "dbeaver -data workspace".

  -nl locale
    Use specified locale instead of default one.
    Example: -nl en (use English locale).

  -vm <java vm path>
    Use Java VM installed in <java vm path> folder instead of default
    location.

  -vmargs <jvm parameters>
    Allows to pass any number of additional parameters to JVM.
    Additional parameters may be used to customize environment or
    3-rd party jdbc drivers.

  -clean
    Clears all Eclipse caches. Use it if DBeaver fails to start after version upgrade.

Licensing
==========================
  GPL 2

Web
==========
  Main web site: http://dbeaver.jkiss.org
  Source code: https://github.com/serge-rider/dbeaver

Authors
==========
  Architect, main programmer, team leader - Serge Rieder (serge@jkiss.org)
  DB2 extension developer - Denis Forveille (titou10.titou10@gmail.com)

Support
=======
  For technical support and assistance, you may find necessary information at the Support page
  (http://dbeaver.jkiss.org/forum) or contact us at support@jkiss.org.

Contacts
==============
  serge@jkiss.org       - Technical support, feature suggestions and any other questions.
