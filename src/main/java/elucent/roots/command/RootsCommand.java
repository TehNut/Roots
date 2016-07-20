package elucent.roots.command;

import java.util.ArrayList;
import java.util.List;

import elucent.roots.capability.mana.ManaProvider;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class RootsCommand implements ICommand {

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "roots";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return I18n.format("roots.command.roots.usage");
	}

	@Override
	public List<String> getCommandAliases() {
		ArrayList<String> aliases = new ArrayList<String>();
		aliases.add("roots");
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length >= 1){
			if (args[0].equals("help")){
				sender.addChatMessage(new TextComponentString(TextFormatting.BOLD+""+TextFormatting.GREEN+"/roots subcommands:"));
				sender.addChatMessage(new TextComponentString("  "+TextFormatting.DARK_GREEN+"/roots setterra <amount> [player] :: Sets a player's current terra, leave blank to target yourself."));
				sender.addChatMessage(new TextComponentString("  "+TextFormatting.DARK_GREEN+"/roots setmaxterra <amount> [player] :: Sets a player's maximum terra, leave blank to target yourself."));
			}
			if (args[0].equals("setterra")){
				if (args.length >= 3){
					EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[2]);
				}
				else {
					if (sender.getCommandSenderEntity() instanceof EntityPlayer){
						if (((EntityPlayer)sender.getCommandSenderEntity()).hasCapability(ManaProvider.manaCapability, null)){
							((EntityPlayer)sender.getCommandSenderEntity()).getCapability(ManaProvider.manaCapability, null).setMana(((EntityPlayer)sender.getCommandSenderEntity()), Integer.valueOf(args[1]));
						}
					}
				}
			}
			if (args[0].equals("setmaxterra")){
				if (args.length >= 3){
					EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[2]);
				}
				else {
					if (sender.getCommandSenderEntity() instanceof EntityPlayer){
						if (((EntityPlayer)sender.getCommandSenderEntity()).hasCapability(ManaProvider.manaCapability, null)){
							((EntityPlayer)sender.getCommandSenderEntity()).getCapability(ManaProvider.manaCapability, null).setMaxMana((float)Integer.valueOf(args[1]));
						}
					}
				}
			}
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		if (server.isSinglePlayer()){
			return true;
		}
		if (server.getPlayerList().getOppedPlayers().getGameProfileFromName(sender.getName()) != null){
			return true;
		}
		return false;
	}

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
